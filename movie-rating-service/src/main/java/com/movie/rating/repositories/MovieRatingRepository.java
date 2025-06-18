package com.movie.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.movie.rating.beans.Rating;

public interface MovieRatingRepository extends MongoRepository<Rating, Integer> {

	public List<Rating> findByRating(float rating);
}