package hibernate.jpa.service.beans;

public enum Rating {
	
	Avg(2.5f), GOOD(5.0f), AboveGood(7.5f), Excellent(10.0f);
	
	private final float rating;

    private Rating(float rating) {
        this.rating = rating;
    }
    
    public float getRating() {
    	return rating;
    }
}
