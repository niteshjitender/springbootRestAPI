package com.springboot.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springbootbackend.model.Employee;

											//            Entity, id
//Spring Data JPA internally provides @Repository Annotation so we no need to add @Repository annotation
// to EmployeeRepository interface
//By default Spring Data JPA made JPARespository methods transactional So we no need to add
// @Transactional annotation in Service class
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
