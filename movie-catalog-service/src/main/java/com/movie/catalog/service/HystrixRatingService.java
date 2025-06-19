package com.movie.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.beans.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HystrixRatingService implements MovieRatingService {

	@Autowired
	private RestTemplate template;
	
	//	Create a service that simulates a failure and fallback
	@HystrixCommand(fallbackMethod = "getFallbackRatingInfo",
		commandProperties = {
		        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
		        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
		},
		threadPoolKey="movieInfoPool",
		threadPoolProperties = {
			    @HystrixProperty(name = "coreSize", value = "10"),
			    @HystrixProperty(name = "maxQueueSize", value = "20")
		}
	)
	public Rating getRatingInfo(int id) {
		
		Rating rating = template.getForObject("http://movie-rating-service/movie-rating/find?id="+id, Rating.class);
		
		return rating;
	}
	
	public Rating getFallbackRatingInfo(int id) {
		
		return new Rating(0, 0.0f, "Dummy Desc");
	}
}
