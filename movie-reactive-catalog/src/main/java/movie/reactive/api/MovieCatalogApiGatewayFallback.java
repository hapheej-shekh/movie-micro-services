package movie.reactive.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class MovieCatalogApiGatewayFallback {

    @GetMapping("/movie-rating")
    public String movieRatingFallback() {
        return "Movie Rating Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/movie-info")
    public String movieInfoFallback() {
        return "Movie Info Service is temporarily unavailable. Please try again later.";
    }
}
