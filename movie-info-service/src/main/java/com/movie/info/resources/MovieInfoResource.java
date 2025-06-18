package com.movie.info.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.info.beans.Movie;
import com.movie.info.beans.Movies;
import com.movie.info.repositories.MovieCatalogRepository;

@RestController

public class MovieInfoResource {

	@Autowired
	private MovieCatalogRepository movieCatalog;
	private boolean flag = true;
	
	@RequestMapping(value="find", method = RequestMethod.GET)
	public ResponseEntity<Movie> getMovie(@PathParam("id") int id) {
		
		if(flag) {
			//movieCatalog.saveAll(init());
			flag = false;
		}
		
		Movie movie = null;
		
		Optional<Movie> list = movieCatalog.findById(id);
		if(list.isPresent())
			movie = list.get();
		
		return new ResponseEntity<Movie>(movie, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="findEnhanced", method = RequestMethod.GET)
	public Movies getMovies() {
		
		return new Movies(movieCatalog.findAll());
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.GET)
	public List<Movie> getMovieList() {
		
		return movieCatalog.findAll();
	}
	
	private List<Movie> init() {
		
		List<Movie> items = new ArrayList<>();
		
		items.add(new Movie(1, "Tiger", "Forest based movie"));
		items.add(new Movie(2, "Ek Tha Tiger", "RAW agency based movie"));
		items.add(new Movie(3, "Tiger Jinda Hai", "RAW agency based sequel movie"));
		items.add(new Movie(4, "Chennai Express", "Romantic movie"));
		items.add(new Movie(5, "Bahubali", "SiFi, VFX based movie"));
		
		return items;
	}
}
