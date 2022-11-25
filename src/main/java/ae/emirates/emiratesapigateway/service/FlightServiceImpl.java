package ae.emirates.emiratesapigateway.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ae.emirates.emiratesapigateway.enums.ExternalEndpoints;
import ae.emirates.emiratesapigateway.model.Flight;
import ae.emirates.emiratesapigateway.ruleengine.PriceRuleEngine;
import ae.emirates.emiratesapigateway.util.Helpers;
import io.netty.handler.timeout.TimeoutException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class FlightServiceImpl implements FlightService{


	CacheService cacheService;
	
	PriceRuleEngine ruleEngine;
	
	public FlightServiceImpl(CacheService cacheService , PriceRuleEngine ruleEngine) {
		this.cacheService = cacheService;
		this.ruleEngine = ruleEngine;
	}
	
	



	public Flux<Object> findFlight(Date date, String from, String to) {
		Flight flight = Flight.builder().date(date).fromAirport(from).toAirport(to).build();
		List<Object> response = new ArrayList<>();
		String key = Helpers.generateKey(flight);
		Optional<Object> cachedValue = Optional.ofNullable(cacheService.get(key));
		return cachedValue.map(Flux::just).orElseGet(()->
		this.callDownstreamEndpoints(flight)
		.doOnEach(respo->{
			if(Objects.nonNull(respo.get()))
				response.add(respo.get());
		})
		.doOnNext(flig->{
			cacheService.put(key, response);
		})

				);
	}



	private Flux<Object> callDownstreamEndpoints(Flight flight) {

		List<Mono<Flight>> strMonoObj = new ArrayList<>(ExternalEndpoints.values().length);
		for(ExternalEndpoints config :ExternalEndpoints.values()) {
			strMonoObj.add(Helpers.createWebClient(config).get().retrieve().bodyToMono(Flight.class)
					.retryWhen(
							Retry.backoff(2, Duration.ofMillis(25))
							.filter(throwable -> throwable instanceof TimeoutException)
							));

		}

		return Flux.fromIterable(strMonoObj)
				.flatMap(mono -> mono);

	}



	@Override
	public Mono<Object> findPrice(String flightNumber, Date date) {
		return Mono.just(ruleEngine.getPrice(flightNumber, date));
	}



}
