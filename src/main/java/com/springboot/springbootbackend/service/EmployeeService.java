package com.springboot.springbootbackend.service;

import java.util.List;

import com.springboot.springbootbackend.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee) ;
	
	List<Employee> getAllEmployees() ;
	
	Employee getEmployeeById(long id) ;
	
	Employee updateEmployeeById(Employee employee, long id);
	
	void deleteEmployee(long id) ;
	

}
