package movie.reactive.beans;

public class Rating {

	private int id;
	private float rating;
	private String ratingDesc;
	
	public Rating() {	}
	
	public Rating(int id, float rating, String ratingDesc) {
		this.id = id;
		this.ratingDesc = ratingDesc;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
