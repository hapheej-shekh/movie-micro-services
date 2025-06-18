package com.movie.info.beans;

public class MovieSummary {

	private String id;
	private String name;
	private String title;
	private String overview;
	
	public MovieSummary() {		}
	
	public MovieSummary(String id, String name, String title, String overview) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.overview = overview;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
}
