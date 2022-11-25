package ae.emirates.emiratesapigateway.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ae.emirates.emiratesapigateway.model.Flight;
import ae.emirates.emiratesapigateway.ruleengine.PriceRuleEngine;
import ae.emirates.emiratesapigateway.util.Helpers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FlightServiceTest {

//	private ClientAndServer mockServer;
	
	private FlightService flightService;
	
	private CacheService cacheService;
	
	@BeforeEach
	public void setupMockServer() {
		cacheService = new CacheServiceImpl();
//		mockServer = ClientAndServer.startClientAndServer(2001);
		flightService = new FlightServiceImpl(cacheService, new PriceRuleEngine());
	}
	
//	@AfterEach
//	public void tearDownServer() {
//		mockServer.stop();
//	}
	
	
	@Test
	void testPositiveCase() {
		Flux<Object> response = flightService.findFlight(new Date(), "abc", "pqr");
		StepVerifier.create(response).expectNextCount(5);
		
	}
	@Test
	void checkOneAfterAnotherCallGetDataFromCache() {
		//first call get data from actual endpoints
		Date date = new Date();
		Flux<Object> response = flightService.findFlight(date, "abc", "pqr");
		StepVerifier.create(response).expectNextCount(5);
		//next call take data from cache
		Flux<Object> response1 = flightService.findFlight(date, "abc", "pqr");
		StepVerifier.create(response1).expectNextCount(5);
		Flight flight = Flight.builder().date(date).fromAirport("abc").toAirport("pqr").build();
		assertNull(cacheService.get(Helpers.generateKey(flight)));
		
	}
	
	
	
}
