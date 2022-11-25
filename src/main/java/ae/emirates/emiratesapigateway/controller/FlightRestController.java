package ae.emirates.emiratesapigateway.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ae.emirates.emiratesapigateway.service.FlightService;
import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class FlightRestController {
	
	
	FlightService flightService;
	
	public FlightRestController(FlightService flightService) {
		this.flightService = flightService;
	}
	
	
	
	
	@GetMapping("/flight/{from}/{to}")
	public Flux<Object> findFlight(@PathVariable(name="from") @NonNull String fromAirport ,@PathVariable(name="to") @NonNull String toAirport ,
			@RequestParam(value="date",required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date date	){
		if(fromAirport.isEmpty() || toAirport.isEmpty()) return Flux.just("from and to airport cannot be empty");
		return flightService.findFlight(date, fromAirport, toAirport);
		
	}
	
	@GetMapping("/price/{flightnumber}")
	public Mono<Object> findPrice(@PathVariable(name="flightnumber") String flightNumber , @RequestParam(value="date",required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		return flightService.findPrice(flightNumber, date);
	}

}
