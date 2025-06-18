package com.starter.jpa.service.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee"
/*, uniqueConstraints = @UniqueConstraint(name="uniqueColum", columnNames = {"mobile", "name"})*/)
@NamedQuery(name = "Employee.findBySalary",
query = "select e.name, e.mobile from Employee e where e.salary = ?1")
public class Employee {

	@Id
	@SequenceGenerator(name="employeeSeq", sequenceName="employeeSeq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employeeSeq")
	private int id;
	private String name;
	private String empType;
	private float salary;
	@Column(name="mobile", nullable = false)
	private long mobile;
	@Embedded
	private Guardian guardian;
	
	//Cascading helps to add/save related property/object
	//Fetch type Lazy will not retrieve data from database
	//Uni-Derectional one to one mapping
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL/* , fetch = FetchType.LAZY */)
	@JoinColumn(name = "address_id", referencedColumnName =  "id")
	private Address address;
	
	@OneToMany(targetEntity = Performance.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "performance_id", referencedColumnName =  "id")
	private List<Performance> performance;
	
	
	
	@OneToMany(targetEntity = Department.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "departmnt_id", referencedColumnName =  "id")
	private List<Department> department;
	
	
	@OneToMany(targetEntity = Achievement.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "achievement_id", referencedColumnName =  "id")
	private List<Achievement> achievement;
	
	public Employee() {		}
	
	public Employee(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	 
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Guardian getGuardian() {
		return guardian;
	}
	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<Performance> getPerformance() {
		return performance;
	}

	public void setPerformance(List<Performance> performance) {
		this.performance = performance;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	public List<Achievement> getAchievement() {
		return achievement;
	}

	public void setAchievement(List<Achievement> achievement) {
		this.achievement = achievement;
	}

	@Override
	public String toString() {
		return "{empId: " + id + ", name: " + name + ", address: " /* + address */ + "}";
	}
}
