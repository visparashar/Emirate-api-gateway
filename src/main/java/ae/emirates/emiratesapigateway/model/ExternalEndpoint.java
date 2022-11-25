package ae.emirates.emiratesapigateway.model;

import ae.emirates.emiratesapigateway.fallback.FallbackStrategy;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExternalEndpoint {
	
	private String url;
	private Integer timeout;
	private FallbackStrategy fallBackStrategy;

}
