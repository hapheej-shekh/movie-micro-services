package com.movie.catalog.service.resilience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.beans.Rating;
import com.movie.catalog.service.MovieRatingService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ResilienceRatingService implements MovieRatingService {

	@Autowired
	private RestTemplate template;
	
	@CircuitBreaker(name = "movieCircuit", fallbackMethod = "fallbackRatingInfo")
	@Bulkhead(name = "moviePool", type = Bulkhead.Type.SEMAPHORE)
	@RateLimiter(name = "movieRateLimiter", fallbackMethod = "fallbackRatingInfo")
	@Retry(name = "movieRetry", fallbackMethod = "fallbackRatingInfo")
	public Rating getRatingInfo(int id) {
		
		Rating rating = template
				.getForObject("http://movie-rating-service/movie-rating/find?id="+id, Rating.class);
		
		return rating;
	}
	
	public Rating fallbackRatingInfo(int id, Throwable t) {
		
		return new Rating(0, 0.0f, "Dummy Desc");
	}
}
