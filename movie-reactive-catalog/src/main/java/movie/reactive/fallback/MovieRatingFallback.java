package movie.reactive.fallback;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class MovieRatingFallback implements MovieRatingClient {

    @Override
    public Rating getRating(int id) {
    	
        // default response if service is down
        return new Rating(id, 0.0F, "Rating service unavailable - fallback");
    }

    @Override
    public List<Rating> getMovies() {
    	
        // return empty list if service is down
        return Collections.singletonList(
            new Rating(0, 0.0F, "No ratings available - fallback")
        );
    }
}
