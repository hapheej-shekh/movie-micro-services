package hibernate.jpa.service.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Table(name = "Company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CompanyGen")
	@SequenceGenerator(name="CompanyGen", sequenceName="CompanySeq", allocationSize=1)
	private int id;
	private String name;
	private String desc;
	private long contact;
	
	//@OneToOne(mappedBy="Company", fetch=FetchType.EAGER)
	//@JoinColumn(name = "id")
	//private Address address;
	//@OneToMany(targetEntity=Package.class, mappedBy="products", fetch=FetchType.EAGER)
	//private List<Package> products;
	
	public Company() {		}
	
	public Company(int id, String name, String desc) {
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Company{id: " + id + ", name: " + name + ", desc: " + desc + ", contact: " + contact;
			//+ ", address: " + address + ", products: " + products + "}";
	}
}
