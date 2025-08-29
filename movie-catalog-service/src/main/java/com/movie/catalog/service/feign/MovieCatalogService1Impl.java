package com.movie.catalog.service.feign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class MovieCatalogService1Impl {

	@Autowired
    private MovieInfoClient movieInfoClient;
    @Autowired
    private MovieRatingClient movieRatingClient;
    
    
	@CircuitBreaker(name="movie-info-service")
    @Retry(name="movie-info-service")
    public List<Movie> getMovies() {
		
        return movieInfoClient.getMovieList();
    }

	@CircuitBreaker(name="movie-rating-service")
    @Retry(name="movie-rating-service")
    public Rating getRating(int movieId) {
		
        return movieRatingClient.getRating(movieId);
    }
}
