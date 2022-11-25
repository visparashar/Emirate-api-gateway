package ae.emirates.emiratesapigateway.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import ae.emirates.emiratesapigateway.enums.ExternalEndpoints;
import ae.emirates.emiratesapigateway.model.Flight;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

public class Helpers {


	public static String generateKey(Flight flight) {
		return flight.getFromAirport()+flight.getToAirport()+flight.getDate();
	}

	public static WebClient createWebClient(ExternalEndpoints externalEndpoint) {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(tcpClient ->
				tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, externalEndpoint.getTimeout())
				.doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(externalEndpoint.getTimeout(), TimeUnit.MILLISECONDS)))
				
						);
		return WebClient.builder()
				.baseUrl(externalEndpoint.getEndpointBaseUrl())
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
	
	public static String randomFlightNumber() {
		return RandomStringUtils.randomAlphabetic(7);
	}

}
