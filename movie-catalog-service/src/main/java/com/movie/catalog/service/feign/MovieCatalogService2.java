package com.movie.catalog.service.feign;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.catalog.beans.CatalogItem;
import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;

/*	This is Fiegen based implementation	*/

@Service
public class MovieCatalogService2 {
	
	@Autowired
    private MovieInfoClient movieInfoClient;
    @Autowired
    private MovieRatingClient movieRatingClient;
    @Autowired
    private CircuitBreakerRegistry cbRegistry;
    @Autowired
    private RetryRegistry retryRegistry;
    
    
    /* CircuitBreaker, Retry rules defined in application.yml
     * YAML/Properties files (resilience4j.*) only defines the rules/policies 
     * (sliding window, retries, thresholds, etc.) 
     * Fallback methods are implemented in Clients  */
    

    public List<CatalogItem> getCatalogs() {

        // CircuitBreaker + Retry for movie-info-service
        CircuitBreaker cbInfo = cbRegistry.circuitBreaker("movie-info-service");
        Retry retryInfo = retryRegistry.retry("movie-info-service");

        Supplier<List<Movie>> movieSupplier = 
            () -> movieInfoClient.getMovieList();

        // decorate with Retry -> CircuitBreaker
        Supplier<List<Movie>> decoratedMovieSupplier =
            Decorators.ofSupplier(movieSupplier)
                      .withCircuitBreaker(cbInfo)
                      .withRetry(retryInfo)
                      .decorate();

        List<Movie> movies = Try.ofSupplier(decoratedMovieSupplier)
                                .recover(ex -> fallbackMovieList())
                                .get();

        
        
        // CircuitBreaker + Retry for movie-rating-service
        CircuitBreaker cbRating = cbRegistry.circuitBreaker("movie-rating-service");
        Retry retryRating = retryRegistry.retry("movie-rating-service");

        return movies.stream().map(movie -> {
            Supplier<Rating> ratingSupplier =
                () -> movieRatingClient.getRating(movie.getId());

            Supplier<Rating> decoratedRatingSupplier =
                Decorators.ofSupplier(ratingSupplier)
                          .withCircuitBreaker(cbRating)
                          .withRetry(retryRating)
                          .decorate();

            Rating rating = Try.ofSupplier(decoratedRatingSupplier)
                               .recover(ex -> fallbackRating(movie.getId()))
                               .get();

            return new CatalogItem(
                movie.getId(),
                movie.getName(),
                movie.getDesc(),
                rating.getRating(),
                rating.getRatingDesc()
            );
        }).collect(Collectors.toList());
    }
    
	private List<Movie> fallbackMovieList() {
	
		return Arrays.asList(new Movie(0, "Dummy Movie", "Movie name not available (Service Down)"));
	}
	
	private Rating fallbackRating(int id) {
    	
        // default response if service is down
        return new Rating(id, 0.0F, "Rating service unavailable - fallback");
    }
}
