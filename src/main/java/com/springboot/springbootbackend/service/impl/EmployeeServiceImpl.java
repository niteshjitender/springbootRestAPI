package com.springboot.springbootbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springbootbackend.exception.ResourceNotFoundException;
import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.repository.EmployeeRepository;
import com.springboot.springbootbackend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository ;

//	If not use @Autowired then we have to instatiate this in constructor 
//	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//		super();
//		this.employeeRepository = employeeRepository;
//	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee) ;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll() ;
	}

	@Override
	public Employee getEmployeeById(long id) {
	
//		Optional<Employee> employee = Optional.of(employeeRepository.getById(id)) ;
//		if(employee.isPresent()) {
//			return employee.get() ;
//		}
//		else {
//			throw new ResourceNotFoundException("Employee", "Id", id) ;
//		}
		
		//The above implementation by lambda expression 
		return employeeRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Employee", "Id", id)) ;
	}

	@Override
	public Employee updateEmployeeById(Employee employee, long id) {
		
		// checking whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Employee", "Id", id) 
				) ;
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee to DB
		employeeRepository.save(existingEmployee) ;
		
		return existingEmployee ;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id) 
			) ;
		employeeRepository.deleteById(id);
	}


}


//Dependency Injection:
//We need to inject employeeRepository dependency
// Two types : setter based
// 			 : constructor based
// We use constructor based dependency when we have mandatory parameters
// when parameters are optional then we use parameters based dependency injection

//Starting with Spring 4.3, if a class which is configured as a Spring bean, has only one constructor, the
//@Autowired annotation can be omitted and Spring will use that constructor and inject all necessary dependencies
