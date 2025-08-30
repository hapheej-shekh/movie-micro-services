package movie.reactive.fallback;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import movie.reactive.beans.Movie;
import movie.reactive.beans.Movies;
import movie.reactive.feign.MovieInfoClient;


@Component
public class MovieInfoFallback implements MovieInfoClient {
	
	@Override
	public Movie getMovieInfo(int id) {

		return new Movie(id, "Dummy Movie", "Movie name not available (Service Down)");
	}

	@Override
	public List<Movie> getMovieList() {
	
		return Arrays.asList(new Movie(0, "Dummy Movie", "Movie name not available (Service Down)"));
	}
	
	@Override
	public Movies getMovies() {

		return new Movies(Arrays.asList(new Movie(0, "Dummy Movie", "Movie name not available (Service Down)")));
	}
}