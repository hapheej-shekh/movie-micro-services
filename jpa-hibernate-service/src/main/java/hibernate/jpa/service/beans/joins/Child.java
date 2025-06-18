package hibernate.jpa.service.beans.joins;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Child")
public class Child {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne(targetEntity=Husband.class, cascade=CascadeType.ALL)
	@JoinColumn(name="father_id", referencedColumnName="id")
	private Husband father;

	@ManyToMany(mappedBy="children")
	private List<Relative> relatives;
	
	public Child() {	
		relatives = new ArrayList<>();
	}
	
	public Child(String name) {
		super();
		this.name = name;
		relatives = new ArrayList<>();
	}
	
	public Child(String name, Husband father) {
		super();
		this.name = name;
		this.father = father;
		relatives = new ArrayList<>();
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

	public Husband getFather() {
		return father;
	}

	public void setFather(Husband father) {
		this.father = father;
	}

	public List<Relative> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<Relative> relatives) {
		this.relatives = relatives;
	}

	@Override
	public String toString() {
		return "Child{id: " + id + ", name: " + name + ", father: " + father + "}";
	}
}
