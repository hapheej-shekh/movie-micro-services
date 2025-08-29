package com.movie.catalog.service.feign;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.catalog.beans.CatalogItem;
import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;

/*	This is Fiegen based implementation	*/

@Service
public class MovieCatalogService1 {

	@Autowired
	private MovieCatalogService1Impl catalogService1Impl;
    
    /* CircuitBreaker, Retry rules defined in application.yml
     * YAML/Properties files (resilience4j.*) only defines the rules/policies 
     * (sliding window, retries, thresholds, etc.) 
     * Fallback methods are implemented in Clients  */
    
	
    public List<CatalogItem> getCatalogs() {
    	
    	List<Movie> movies = catalogService1Impl.getMovies();
    	
    	return movies.stream().map(movie -> {
    		Rating rating = catalogService1Impl.getRating(movie.getId());
    		return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
    	}).collect(Collectors.toList());
    }
}
