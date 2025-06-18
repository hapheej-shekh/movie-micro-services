package com.movie.info.resources;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movie.info.beans.MovieSummary;

@RestController
@RequestMapping("moviedb")
public class MovieDBResource {

	@Value("${api.key}")
	private String apiKey;
	@Autowired
	private RestTemplate template;

	@RequestMapping(value="fetch")
	public void getMovie(@PathParam("id") int id) {
		MovieSummary m = template.getForObject("https://api.themoviedb.org/3/movie/"+id+"?api_key="+apiKey, MovieSummary.class);
		System.out.print(m);
	}
}
