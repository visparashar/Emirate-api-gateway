package ae.emirates.emiratesapigateway.service;

import java.util.Date;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {
	
	Flux<Object> findFlight(Date date , String from , String to);

	Mono<Object> findPrice(String flightNumber, Date date);

}
