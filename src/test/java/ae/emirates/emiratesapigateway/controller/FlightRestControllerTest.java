package ae.emirates.emiratesapigateway.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ae.emirates.emiratesapigateway.service.FlightService;
import ae.emirates.emiratesapigateway.service.FlightServiceImpl;

public class FlightRestControllerTest {
	
	private FlightService flightServiceMock;
	private FlightRestController flightRestController;
	
	
	@BeforeEach
	public void setup() {
		flightServiceMock = Mockito.mock(FlightServiceImpl.class);
		flightRestController = new FlightRestController(flightServiceMock);
	}
	
	
	@Test
	public void testFindFlights() {
		try {
		flightRestController.findFlight(null, null, new Date());
		}catch(Exception ex) {
			assertThat(ex instanceof NullPointerException);
		}
	}
	

}
