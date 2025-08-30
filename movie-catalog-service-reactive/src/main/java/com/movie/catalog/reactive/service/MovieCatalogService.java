package com.movie.catalog.reactive.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.catalog.reactive.beans.CatalogItem;
import com.movie.catalog.reactive.beans.Movie;
import com.movie.catalog.reactive.beans.Rating;
import com.movie.catalog.reactive.feign.MovieInfoClient;
import com.movie.catalog.reactive.feign.MovieRatingClient;


@Service
public class MovieCatalogService {

	@Autowired
	private MovieRatingClient ratingClient;
	@Autowired
    private MovieInfoClient infoClient;
	
	
	public List<CatalogItem> getCatalogs() {
    	
    	List<Movie> movies = infoClient.getMovieList();
    	
    	return movies.stream().map(movie -> {
    		Rating rating = ratingClient.getRating(movie.getId());
    		return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
    	}).collect(Collectors.toList());
    }
}
