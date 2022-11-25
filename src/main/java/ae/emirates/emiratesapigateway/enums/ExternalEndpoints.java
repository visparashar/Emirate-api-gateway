package ae.emirates.emiratesapigateway.enums;

import ae.emirates.emiratesapigateway.fallback.FallbackStrategy;
import lombok.Getter;

public enum ExternalEndpoints {
	
	ENDPOINT1("https://run.mocky.io/v3/bb834ae5-da60-496a-b895-5ed93a19a76c",500,new FallbackStrategy()),
	ENDPOINT2("https://run.mocky.io/v3/bb834ae5-da60-496a-b895-5ed93a19a76c",500,new FallbackStrategy()),
	ENDPOINT3("https://run.mocky.io/v3/bb834ae5-da60-496a-b895-5ed93a19a76c",500,new FallbackStrategy()),
	ENDPOINT4("https://run.mocky.io/v3/bb834ae5-da60-496a-b895-5ed93a19a76c",500,new FallbackStrategy()),
	ENDPOINT5("https://run.mocky.io/v3/bb834ae5-da60-496a-b895-5ed93a19a76c",500,new FallbackStrategy());
	
	
	ExternalEndpoints(String endpointBase,Integer timeout , FallbackStrategy strategy) {
		this.endpointBaseUrl = endpointBase;
		this.timeout = timeout;
		this.strategy = strategy;
	}
	
	
	@Getter
	private final String endpointBaseUrl;
	@Getter
	private final FallbackStrategy strategy;
	@Getter
	private final Integer timeout;
	

}
