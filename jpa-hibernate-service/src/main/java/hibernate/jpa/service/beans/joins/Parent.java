package hibernate.jpa.service.beans.joins;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Parent")
public class Parent extends GrandParent {

	private String name;
	
	public Parent() {	}
	
	public Parent(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Parent{name: " + name + "}";
	}
}
