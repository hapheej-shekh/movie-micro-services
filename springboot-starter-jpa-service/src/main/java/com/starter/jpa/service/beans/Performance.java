package com.starter.jpa.service.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="performance")
public class Performance {

	@Id
	@SequenceGenerator(name="EmpPerfSeq", sequenceName="EmpPerfSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EmpPerfSeq")
	private int id;
	private String type;
	private float grade;
	private float bonus;
	private String description;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Employee employee;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
