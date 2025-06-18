package com.starter.jpa.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.starter.jpa.service.beans.Employee;

@Repository
//public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//Query creation from method names
	public List<Employee> findByName(String name);
	public List<Employee> findByNameContaining(String name); //Contains match
	public List<Employee> findByNameAndMobile(String name, long mobile);
	public List<Employee> findByEmpTypeNotNull();
	public List<Employee> findByEmpType(String empType);
	public List<Employee> findByNameAndEmpTypeNotNull(String name);
	public List<Employee> findByGuardianName(String name);
	
	// Named Query [Defined In Entity Class]
	public String[] findBySalary(float salary);
	
	// Query/JPQL
	@Query("select avg(e.salary) from Employee e group by e.empType")
	public List<Integer> averageSalary();
	
	// Native Query
	@Query(value = "select avg(salary) from employees group by emp_type", nativeQuery = true)
	public List<Integer> nativeAverageSalary();
	
	// Native Named Query	[If number of binding/input parameter are more]
	@Query(value = "select * from employees where name=:empName and mobile=:empMobile", nativeQuery = true)
	public List<Employee> nativeNamedAverageSalary(String empName, long empMobile);
	
	@Modifying	//Shows updating record/s
	@Transactional	//Shows updating record/s using transaction start->update->commit
	@Query(value = "update employees set salary=:Salary where emp_id=:empId", nativeQuery = true)
	public void updateSalary(int empId, float Salary);
}
