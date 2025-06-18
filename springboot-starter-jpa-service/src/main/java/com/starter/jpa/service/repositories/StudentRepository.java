package com.starter.jpa.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starter.jpa.service.joins.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
