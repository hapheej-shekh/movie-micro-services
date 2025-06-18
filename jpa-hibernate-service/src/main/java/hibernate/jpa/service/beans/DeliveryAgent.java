package hibernate.jpa.service.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import hibernate.jpa.service.beans.jpql.Address;

public class DeliveryAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DeliveryGen")
	@SequenceGenerator(name = "DeliveryGen", sequenceName = "DeliverySeq", initialValue = 1)
	private int id;
	private String firstName;
	private String lastName;
	private long mobile;
	private String status;
	private float rating;
	
	private Address address;

	public DeliveryAgent() {	}
	
	public DeliveryAgent(int id, String firstName, String lastName, long mobile, String status, float rating) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.status = status;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DeliveryAgent{id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", mobile: " + mobile
				+ ", status: " + status + ", rating: " + rating + ", address: " + address + "}";
	}
}
