package hibernate.jpa.service.beans.jpql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "JPQLEntity")
@NamedQuery(name="find by id", query="select J from JPQLEntity J where J.id = :id")
public class JPQLEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(length=15, nullable=false, updatable=true)
	private String firstName;
	
	@Column(nullable=true, updatable=true)
	private String lastName;
	
	private String location;
	private int mobile;
	private int zipcode;
	
	public JPQLEntity() {		}
	
	public JPQLEntity(String firstName, int mobile) {
		super();
		this.firstName = firstName;
		this.mobile = mobile;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "JPQLEntity{id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", location=" + location
				+ ", mobile=" + mobile + ", zipcode=" + zipcode + "}";
	}
}
