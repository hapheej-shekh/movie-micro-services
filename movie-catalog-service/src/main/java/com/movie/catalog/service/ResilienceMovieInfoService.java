package com.movie.catalog.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ResilienceMovieInfoService implements MovieInfoService {

	@Autowired
	private RestTemplate template;
	
	/* spring-boot-starter-aop -> Needed to fallback methods work */
	
	// @CircuitBreaker, @Bulkhead, @RateLimiter, @Retry -> From Resilience
	
	@CircuitBreaker(name = "movieCircuit", fallbackMethod = "fallbackMovieInfo")
	@Bulkhead(name = "moviePool", type = Bulkhead.Type.SEMAPHORE)
	@RateLimiter(name = "movieRateLimiter", fallbackMethod = "fallbackMovieInfo")
	@Retry(name = "movieRetry", fallbackMethod = "fallbackMovieInfo")
	public Movie[] getMovieInfo() {
		
		Movie[] ratedMovies = template
				.getForObject("http://movie-info-service/movie-info/findAll", Movie[].class);
		
		return ratedMovies;
	}
	
	public Movie[] fallbackMovieInfo(Throwable t) {
		
		Movie[] ratedMovies = new Movie[1];
		ratedMovies[0] = new Movie(0, "Dummy", "Dummy Desc");
		
		return ratedMovies;
	}
	
	// Bulkhead.Type.THREADPOOL only works with CompletableFuture
	
	@CircuitBreaker(name = "movieCircuit", fallbackMethod = "fallbackRatingInfo")
	@Bulkhead(name = "moviePool", type = Bulkhead.Type.THREADPOOL)
	@RateLimiter(name = "movieRateLimiter", fallbackMethod = "fallbackRatingInfo")
	@Retry(name = "movieRetry", fallbackMethod = "fallbackRatingInfo")
	public CompletableFuture<Movie> getMovieInfo1(int id) {
		
		return CompletableFuture.supplyAsync(() ->
        template.getForObject("http://movie-rating-service/movie-info/find?id="+id, Movie.class));
	}
	
	public Rating fallbackRatingInfo(int id, Throwable t) {
		
		Rating rating = new Rating(0, 0.0f, "Dummy Desc");
		
		return rating;
	}

	@Override
	public Movie getMovieInfo(int id) {

		return new Movie(1, "Dummy", "Logic not implemented");
	}
}
