package com.springboot.springbootbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.service.EmployeeService;

@RestController
@RequestMapping("/api/employees") //defining the base URL 
@CrossOrigin("*")
public class EmployeeController {
	
	private EmployeeService employeeService ;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// build create employee REST API 
	//We are using ResponseEntity class because we can provide complete response details, like status, header can be added
	//@RequestBody annotation allows us to retrieve the request's body and automatically convert it to Java Object
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED) ;
	}
	
	//build get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees() ;
	}
	
    // build get employee by id REST API
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK) ;
	}

	// build update employee REST API
	//http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployeeById(employee, id), HttpStatus.OK) ;
		
	}
	
	//build delete employee REST API
	//http://localhost:8080/api/employees/2
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
	
		//delete employee from DB 
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK); 
	}
	
}


//@RestController is a convenient annotation that combines @Controller and @ResponseBody, which eliminates the
//need to annotate every request handling method of the controller class with the @ResponseBody annotation.