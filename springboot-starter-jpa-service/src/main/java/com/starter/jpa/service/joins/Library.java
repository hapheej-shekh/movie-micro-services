package com.starter.jpa.service.joins;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
public class Library {

	@Id
	//@SequenceGenerator(name="LibrarySeq", sequenceName="LibrarySeq", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LibrarySeq")
	private int id;
	private String name;
	private String location;
	
	//“many” Student records map to “one” Library
	@ManyToOne(targetEntity = Student.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName =  "id")
	private Student student;
	
	public Library() {		}
	
	public Library(int id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
