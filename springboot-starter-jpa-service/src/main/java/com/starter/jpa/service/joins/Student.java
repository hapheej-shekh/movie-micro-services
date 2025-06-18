package com.starter.jpa.service.joins;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

	@Id
	//@SequenceGenerator(name="StudentSeq", sequenceName="StudentSeq", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="StudentSeq")
	private int id;
	private String name;
	private String depart;
	
	@OneToOne(targetEntity = Library.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id1", referencedColumnName =  "id")
	private Library library1;
	
	@OneToMany(targetEntity = Library.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id2", referencedColumnName =  "id")
	private List<Library> library2;
	
	/*	Better to define in Library class
	 * 
	@ManyToOne(targetEntity = Library.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id3", referencedColumnName =  "id")
	private List<Library> library3;
	*/
	//student_library_tbl-> name of join table
	
	@ManyToMany(targetEntity = Library.class, cascade = CascadeType.ALL)
	@JoinTable(name="student_library_tbl", 
	joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="library_id4", referencedColumnName = "id"))
	private List<Library> library4;
	
	public Student() { 	}
	
	public Student(int id, String name, String depart) {
		super();
		this.id = id;
		this.name = name;
		this.depart = depart;
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

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Library getLibrary1() {
		return library1;
	}

	public void setLibrary1(Library library1) {
		this.library1 = library1;
	}

	public List<Library> getLibrary2() {
		return library2;
	}

	public void setLibrary2(List<Library> library2) {
		this.library2 = library2;
	}
	public List<Library> getLibrary4() {
		return library4;
	}

	public void setLibrary4(List<Library> library4) {
		this.library4 = library4;
	}
}
