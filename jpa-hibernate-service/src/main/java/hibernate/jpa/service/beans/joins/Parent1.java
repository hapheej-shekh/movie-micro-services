package hibernate.jpa.service.beans.joins;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Parent1 extends GrandParent1 {

	private String firstName;
	private String lastName;
	
	public Parent1() {	}
	
	public Parent1(String firstName, String lastName) {
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
