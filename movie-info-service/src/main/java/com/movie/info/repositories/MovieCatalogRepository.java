package com.movie.info.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.info.beans.Movie;

public interface MovieCatalogRepository extends MongoRepository<Movie, Integer> {

	List<Movie> findByName(String name);
}
