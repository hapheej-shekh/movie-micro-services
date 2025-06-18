package com.starter.jpa.service.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.starter.jpa.service.Type;
import com.starter.jpa.service.beans.Employee;
import com.starter.jpa.service.beans.Guardian;
import com.starter.jpa.service.repositories.EmployeeRepository;

@SpringBootTest	//This impact database/tables
//@DataJpaTest	// This not impact database/tables
public class EmployeeTest {

	@Autowired
	private EmployeeRepository empRepo;
	
	//@Test
	public void saveEmps() {
		
		Guardian g = new Guardian();
		g.setEmail("arham.vahleen@mail.com");
		g.setMobile(987523);
		g.setName("Hapheej");
		
		List<Employee> emps = new ArrayList<>();
		Employee e1 = new Employee("Arham", null);
		e1.setEmpType(Type.Permanent.getType());
		e1.setMobile(97538012);
		e1.setSalary(52.0f);
		e1.setGuardian(g);
		
		Employee e2 = new Employee("Hapheej", null);
		e2.setEmpType(Type.Permanent.getType());
		e2.setMobile(97538012);
		e2.setSalary(52.0f);
		
		Employee e3 = new Employee("Aastana", null);
		e3.setEmpType(Type.Permanent.getType());
		e3.setMobile(97538012);
		e3.setSalary(52.0f);
		e3.setGuardian(g);
		
		emps.add(e1);emps.add(e2);emps.add(e3);
		
		empRepo.saveAll(emps);
	}
	
	@Test
	public void findEmps() {
		
		List<Employee> e = null;
		
		e = empRepo.findByName("Arham");
		e = empRepo.findByName("Arham1");
		e = empRepo.findByNameContaining("Arham Vahleen");
		e = empRepo.findByNameContaining("Arha");
		e = empRepo.findByNameAndMobile("Arham1", 97538012);
		e = empRepo.findByEmpTypeNotNull();
		e = empRepo.findByNameAndEmpTypeNotNull("Arham1");
		e = empRepo.findByGuardianName("Hapheej");
		
		//Named Query [Defined In Entity Class]
		String [] NameMobile = empRepo.findBySalary(52);
		
		System.out.println(""+e+NameMobile);
	}
}
