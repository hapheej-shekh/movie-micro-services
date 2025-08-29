package com.movie.catalog.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.movie.catalog.beans.CatalogItem;
import com.movie.catalog.beans.Movie;
import com.movie.catalog.beans.Rating;

@RestController
@RequestMapping("webclient")
public class MovieCatalogApiWebClient {

	@Autowired
	private WebClient.Builder builder; //WebClient instead of RestTemplate
	
	@RequestMapping(value="find", method = RequestMethod.GET)
	public ResponseEntity<CatalogItem> getCatalog(@RequestParam("id") int id) {
		
		return null;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.GET)
	public ResponseEntity<List<CatalogItem>> getCatalogs() {
		
		ResponseEntity<List<Movie>> movies = null;
		List<CatalogItem> webClientResult = null;
		
		//Micro Service Communication Using Rest Template
		/*
	 	List<CatalogItem> restTemplateResult = null;
		ResponseEntity<Movie[]> moviesData = template.getForEntity("http://localhost:8082/movie-info/findAll", Movie[].class);
		
		restTemplateResult = Arrays.stream(moviesData.getBody()).map(movie->{
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), 0.0f, "rating desc");
		}).collect(Collectors.toList());
		return new ResponseEntity<List<CatalogItem>>(restTemplateResult, HttpStatus.FOUND);
		*/
		
		//Using WebClient
		
		try {
			movies = builder.build().get().uri("http://movie-info-service/movie-info/findAll")
			.retrieve().toEntityList(Movie.class).block();
		} catch(Exception ex) {
			List<Movie> lst = new ArrayList<>();
			movies = new ResponseEntity<List<Movie>>(lst, HttpStatus.NOT_FOUND);
		}
		
		webClientResult = movies.getBody().stream().map(movie->{
			
			Rating rating = null;
			try {
				rating = builder.build().get().uri("http://movie-rating-service/movie-rating/find?id="+movie.getId())
					.retrieve().bodyToMono(Rating.class).block();
			} catch(Exception ex) {
				rating = new Rating();
			}
			return new CatalogItem(movie.getId(), movie.getName(), movie.getDesc(), rating.getRating(), rating.getRatingDesc());
		}).collect(Collectors.toList());		
		
		return new ResponseEntity<List<CatalogItem>>(webClientResult, HttpStatus.FOUND);
	}
}
