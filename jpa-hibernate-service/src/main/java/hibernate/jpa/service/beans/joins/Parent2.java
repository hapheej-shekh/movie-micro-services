package hibernate.jpa.service.beans.joins;

import javax.persistence.Entity;

@Entity
public class Parent2 extends GrandParent2 {

	private String firstName;
	private String lastName;
	
	public Parent2() {	}
	
	public Parent2(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "Parent1{firstName: " + firstName + ", lastName: " + lastName + "}";
	}
}
