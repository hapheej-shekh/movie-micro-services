package com.starter.jpa.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starter.jpa.service.beans.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
