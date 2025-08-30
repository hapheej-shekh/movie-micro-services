package movie.reactive.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.reactive.beans.CatalogItem;
import movie.reactive.services.MovieCatalogService;


@RestController
@RequestMapping("/gateway")
public class MovieCatalogApiGateway {

	@Autowired private MovieCatalogService catalogService;
	
	@GetMapping("/catalog")
	public ResponseEntity<?> getCatalogs1() {
		
		List<CatalogItem> catalog = catalogService.getCatalogs();
		
		return new ResponseEntity<List<CatalogItem>>(catalog, HttpStatus.OK);
	}
}
