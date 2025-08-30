package movie.reactive.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.reactive.beans.CatalogItem;
import movie.reactive.beans.Movie;
import movie.reactive.beans.Rating;
import movie.reactive.feign.MovieInfoClient;
import movie.reactive.feign.MovieRatingClient;



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
