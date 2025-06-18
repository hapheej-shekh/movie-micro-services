package hibernate.jpa.service.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import hibernate.jpa.service.beans.jpql.Address;

public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PackageGen")
	@SequenceGenerator(name = "PackageGen", sequenceName = "PackageSeq", initialValue = 1)
	private int id;
	private String name;
	private String desc;
	
	private Address delivery;
	private Address pickUp;
	private Customer customer;
	private Company company;
	
	public Package() {		}
	
	public Package(int id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
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

	public Address getDelivery() {
		return delivery;
	}

	public void setDelivery(Address delivery) {
		this.delivery = delivery;
	}

	public Address getPickUp() {
		return pickUp;
	}

	public void setPickUp(Address pickUp) {
		this.pickUp = pickUp;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Package{id: " + id + ", name: " + name + ", desc: " + desc + ", delivery: " + delivery + ", pickUp: "
				+ pickUp + ", customer: " + customer + ", company: " + company + "}";
	}
}
