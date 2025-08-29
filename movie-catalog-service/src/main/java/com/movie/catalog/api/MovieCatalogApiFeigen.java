package com.movie.catalog.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.catalog.beans.CatalogItem;
import com.movie.catalog.service.feign.MovieCatalogService1;
import com.movie.catalog.service.feign.MovieCatalogService2;

@RestController
@RequestMapping("/feigen")
public class MovieCatalogApiFeigen {

	@Autowired
	private MovieCatalogService1 movieCatalog1;
	@Autowired
	private MovieCatalogService2 movieCatalog2;
	
	
	@GetMapping("/catalog1")
	public ResponseEntity<?> getCatalogs1() {
		
		List<CatalogItem> catalog = movieCatalog1.getCatalogs();
		
		return new ResponseEntity<List<CatalogItem>>(catalog, HttpStatus.OK);
	}
	
	@GetMapping("/catalog2")
	public ResponseEntity<?> getCatalogs2() {
		
		List<CatalogItem> catalog = movieCatalog2.getCatalogs();
		
		return new ResponseEntity<List<CatalogItem>>(catalog, HttpStatus.OK);
	}
}
