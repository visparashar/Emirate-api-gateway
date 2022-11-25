package ae.emirates.emiratesapigateway.service;

public interface CacheService {
	
		void put(String key , Object response);
		
		Object get(String key);

}
