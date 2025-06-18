package hibernate.jpa.service.beans.joins;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Husband")
public class Husband {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToOne(targetEntity=Wife.class, cascade=CascadeType.ALL)
	private Wife wife;
	
	@OneToMany(targetEntity=Relative.class, cascade=CascadeType.ALL)
	@JoinColumn(name="relative_id", referencedColumnName="id")
	private List<Relative> relatives;
	
	
	public Husband() {
		relatives = new ArrayList<>();
	}

	public Husband(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

	public List<Relative> getRelatives() {
		return relatives;
	}
	
	public void getMarry() {
		
		Wife w = new Wife("Aastana");
		this.setWife(w);
	}

	public void setRelatives(List<Relative> relatives) {
		this.relatives = relatives;
	}

	@Override
	public String toString() {
		return "Husband{id: " + id + ", name: " + name + ", wife: " + wife + "}";
	}
}
