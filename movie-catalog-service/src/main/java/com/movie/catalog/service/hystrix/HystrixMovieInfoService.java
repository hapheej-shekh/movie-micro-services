package com.movie.catalog.service.hystrix;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;
import com.movie.catalog.service.MovieInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HystrixMovieInfoService implements MovieInfoService {

	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
	public Movie[] getMovieInfo() {
		
		Movie[] ratedMovies = template.getForObject("http://movie-info-service/movie-info/findAll", Movie[].class);
		
		return ratedMovies;
	}
	
	public Movie[] getFallbackMovieInfo() {
		
		Movie[] ratedMovies = new Movie[1];
		ratedMovies[0] = new Movie(0, "Dummy", "Dummy Desc");
		
		return ratedMovies;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackMovieInfo",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
			})
	public Movie getMovieInfo(int id) {
		
		Movie movie = template.getForObject("http://movie-rating-service/movie-info/find?id="+id, Movie.class);
		
		return movie;
	}
	
	public Rating fallbackMovieInfo(int id) {
		
		Rating rating = new Rating(0, 0.0f, "Dummy Desc");
		
		return rating;
	}

	@Override
	public CompletableFuture<Movie> getMovieInfo1(int id) {
		
		return CompletableFuture.supplyAsync(() -> new Movie(1, "Dummy", "Logic not implemented"));
	}
}
