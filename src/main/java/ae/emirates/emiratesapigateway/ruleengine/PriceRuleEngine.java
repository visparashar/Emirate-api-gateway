package ae.emirates.emiratesapigateway.ruleengine;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class PriceRuleEngine {
	
	public static Map<String,Double> inMemoryMap= new ConcurrentHashMap<>();
	
	public Double getPrice(String flightNumber , Date date) {
		//have put the default value just because it will not fail, otherwise we will expect the data is always present in the memory cache.
		 return inMemoryMap.getOrDefault(flightNumber, 10.56);
	}

}
