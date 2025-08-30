package movie.reactive.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movie.reactive.beans.Rating;
import movie.reactive.fallback.MovieRatingFallback;


// movie-rating-service --> service name registered in Eureka
@FeignClient(
		name = "movie-rating-service",
		path = "${urls.movie-rating}",
		fallback = MovieRatingFallback.class
)
public interface MovieRatingClient {

	@GetMapping("/find")
	public Rating getRating(@RequestParam("id") int id);
	
	@GetMapping("/rating")
	public List<Rating> getMovies();
}
