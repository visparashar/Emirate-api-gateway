package ae.emirates.emiratesapigateway.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import lombok.NonNull;

@Service
public class CacheServiceImpl implements CacheService{

	@Value("${time-to-live:24}")
	long TTL=24l;

	private final Cache<String, Object> FLIGHT_NUMBER_CACHE = Caffeine.newBuilder()
			.expireAfterWrite(Duration.ofHours(TTL))
			.build();


	public void put(@NonNull String key, Object value) {
		FLIGHT_NUMBER_CACHE.put(key, value);
	}

	public Object get(String key) {
		return FLIGHT_NUMBER_CACHE.getIfPresent(key);
		
	}

}
