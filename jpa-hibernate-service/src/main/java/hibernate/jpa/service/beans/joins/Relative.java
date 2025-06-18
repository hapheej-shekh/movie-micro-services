package hibernate.jpa.service.beans.joins;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Relative")
public class Relative {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String relation;
	
	//Cascade Parent To Child Class
	@ManyToMany(cascade= {CascadeType.ALL, CascadeType.ALL})
	@JoinTable(name="Relative_Child", 
		joinColumns= @JoinColumn(name="relative_id"),
		inverseJoinColumns= @JoinColumn(name="child_id")
	)
	private List<Child> children;
	
	public Relative() {		
		children = new ArrayList<>();
	}
	
	public Relative(String name, String relation) {
		super();
		this.name = name;
		this.relation = relation;
		children = new ArrayList<>();
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

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Relative{id: " + id + ", name: " + name + ", relation: " + relation + "}";
	}
}
