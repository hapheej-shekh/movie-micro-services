package com.movie.catalog.reactive.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.catalog.reactive.beans.Movie;
import com.movie.catalog.reactive.beans.Movies;
import com.movie.catalog.reactive.fallback.MovieInfoFallback;


// movie-info-service --> service name registered in Eureka
@FeignClient(
		name = "movie-info-service",
		path = "${urls.movie-info}",
		fallback = MovieInfoFallback.class
)
public interface MovieInfoClient {

	@GetMapping("/find")
    Movie getMovieInfo(@RequestParam("id") int id);
	
	@GetMapping("/findEnhanced")
	public Movies getMovies();
	
	@GetMapping("/findAll")
	public List<Movie> getMovieList();
}
