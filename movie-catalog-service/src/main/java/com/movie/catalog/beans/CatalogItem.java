package com.movie.catalog.beans;

public class CatalogItem {

	private int id;
	private String name;
	private String desc;
	private float rating;
	private String ratingDesc;

	public CatalogItem() {		}
	
	public CatalogItem(int id, String name, String desc, float rating, String ratingDesc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.rating = rating;
		this.ratingDesc = ratingDesc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getRatingDesc() {
		return ratingDesc;
	}

	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}
}
