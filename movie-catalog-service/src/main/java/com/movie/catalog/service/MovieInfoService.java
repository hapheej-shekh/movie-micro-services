package com.movie.catalog.service;

import java.util.concurrent.CompletableFuture;

import com.movie.catalog.beans.Movie;

public interface MovieInfoService {

	public Movie[] getMovieInfo();
	public Movie getMovieInfo(int id);
	public CompletableFuture<Movie> getMovieInfo1(int id);
}
