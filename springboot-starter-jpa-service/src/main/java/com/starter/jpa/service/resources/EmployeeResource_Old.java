package com.starter.jpa.service.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starter.jpa.service.beans.Achievement;
import com.starter.jpa.service.beans.Address;
import com.starter.jpa.service.beans.Employee;
import com.starter.jpa.service.repositories.EmployeeRepository;

//@RestController
//@RequestMapping("employee")
public class EmployeeResource_Old {

	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private JpaTransactionManager manager;
	
	//@RequestMapping("achievementWise")
	public ResponseEntity<List<Employee>> getEmpDeptwise() {
		
		List<Employee> emps = null;//empRepo.findByAchievement();
		
		return new ResponseEntity<List<Employee>>(emps, HttpStatus.FOUND);
	}
	
	//@RequestMapping("fetchAll")
	public ResponseEntity<List<Employee>> fetchAllEmp() {
		
		List<Employee> emps = new ArrayList<>();
		Iterable<Employee> empsItr = empRepo.findAll();
		empsItr.forEach(action->emps.add(action));
		
		return new ResponseEntity<List<Employee>>(emps, HttpStatus.OK);
	}
	
	//@RequestMapping("initialize")
	public void init() {
		
		List<Employee> emps = new ArrayList<>();
		Employee e1 = new Employee("Arham", new Address(1, "Temporary", "Sunka Chauraha", "Sunka Chauraha", 480007));
		Employee e2 = new Employee("Hapheej", new Address(2, "Temporary", "Sunka Chauraha", "Sunka Chauraha", 480007));
		Employee e3 = new Employee("Aastana", new Address(3, "Temporary", "Sunka Chauraha", "Sunka Chauraha", 480007));
		
		List<Achievement> ach = new ArrayList<>();
		Achievement a1 = new Achievement(1, "Annual Recognition", "Precious award", 7.6f);
		

		//List<Performance> permnce = new ArrayList<>();
		//Performance p1 = new Performance(101, "Annual", 8.2f, 22.0f, "Performed best in annual summit");
		
		
		//empRepo.saveAll(emps);
		
		EntityManagerFactory a = manager.getEntityManagerFactory();
		EntityManager b = a.createEntityManager();
		//b.getTransaction().begin();
		//b.persist(p1);
		b.persist(a1);
		
		//ach.add(a1);permnce.add(p1);
		//e1.setPerformance(permnce);e2.setPerformance(permnce);e3.setPerformance(permnce);
		//e1.setAchievement(ach);e2.setAchievement(ach);e3.setAchievement(ach);
		emps.add(e1);emps.add(e2);emps.add(e3);
		
		b.persist(emps);
		
		//b.getTransaction().commit();
		b.close();
	}
}
