package ae.emirates.emiratesapigateway.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CacheServiceTest {
	
	public CacheService cacheService = new CacheServiceImpl();
	
	
	@Test
	public void testPut_whenNotAvailable() {
		assertNull(cacheService.get("test"));
	}
	@Test
	public void testPut_whenAvailable() {
		cacheService.put("test", "test1");
		assertNotNull(cacheService.get("test"));
	}

}
