package com.movie.catalog.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.beans.CatalogItem;
import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Movies;
import com.movie.catalog.beans.Rating;
import com.movie.catalog.service.MovieInfoService;
import com.movie.catalog.service.MovieRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("resttemplate")
public class MovieCatalogResourceRestTemplate {

	@Autowired
	private RestTemplate template;
	@Autowired
	private MovieInfoService movieInfoService;
	@Autowired
	private MovieRatingService ratingService;
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<CatalogItem>> getCatalogs(@PathVariable("userId") int userId) {
		
		List<CatalogItem> restTemplateResult = null;
		
		Movie[] ratedMovies = movieInfoService.getMovieInfo();
		
		restTemplateResult =Arrays.stream(ratedMovies).map(movie->{
			
			Rating rating = ratingService.getRatingInfo(movie.getId());
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<CatalogItem> getCatalog(@PathVariable("id") int id) {
		
		return null;
	}
	
	//@HystrixCommand(fallbackMethod = "getFallbackAllCatalogs")
	@GetMapping("findAllSeparate")
	public ResponseEntity<List<CatalogItem>> getAllCatalogs() {
		
		List<CatalogItem> restTemplateResult = null;
		
		Movie[] ratedMovies = movieInfoService.getMovieInfo();
		
		restTemplateResult =Arrays.stream(ratedMovies).map(movie->{
			
			Rating rating = ratingService.getRatingInfo(movie.getId());
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	}
	
	//Hystrix fallback method signature should be same
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	@GetMapping("findAll")
	public ResponseEntity<List<CatalogItem>> getCatalogs() {
		
		List<CatalogItem> restTemplateResult = null;
		
		//Micro Service Communication Using Rest Template
		Movie[] ratedMovies = template.getForObject("http://movie-info-service/movie-info/findAll", Movie[].class);
		
		restTemplateResult =Arrays.stream(ratedMovies).map(movie->{
			
			Rating rating = template.getForObject("http://movie-rating-service/movie-rating/find?id="+movie.getId(), Rating.class);
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	}
	
	//Hystrix fallback method signature should be same
	public ResponseEntity<List<CatalogItem>> getFallbackCatalog() {
		
		List<CatalogItem> restTemplateResult = null;
		
		Rating rating = new Rating(0, 0.0f, "Dummy Desc");
		
		Movie[] ratedMovies = template.getForObject("http://movie-info-service/movie-info/findAll", Movie[].class);
		
		restTemplateResult =Arrays.stream(ratedMovies).map(movie->{
			
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	}
	
	@GetMapping("findEureka")
	public ResponseEntity<List<CatalogItem>> getCatalogsUsingEureka() {
		
		List<CatalogItem> restTemplateResult = null;
		
		//Micro Service Communication Using Rest Template
		Movie[] ratedMovies = template.getForObject("http://movie-info-service/movie-info/findAll", Movie[].class);
		
		restTemplateResult =Arrays.stream(ratedMovies).map(movie->{
			
			Rating rating = template.getForObject("http://movie-rating-service/movie-rating/find?id="+movie.getId(), Rating.class);
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	}
	
	
	//@RequestMapping(value="find", method = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("findEnhanced")
	public ResponseEntity<List<CatalogItem>> getEnhancedCatalogs() {
		
		List<CatalogItem> restTemplateResult = null;
		
		//Micro Service Communication Using Rest Template
		Movies ratedMovies = template.getForObject("http://movie-info-service/movie-info/findEnhanced", Movies.class);
		
		restTemplateResult =ratedMovies.getMovies().stream().map(movie->{
			
			Rating rating = template.getForObject("http://movie-rating-service/movie-rating/find?id="+movie.getId(), Rating.class);
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());
		
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
	
	}
}
