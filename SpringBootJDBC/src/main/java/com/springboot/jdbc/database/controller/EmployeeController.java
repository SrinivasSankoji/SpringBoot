package com.springboot.jdbc.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jdbc.database.model.Employee;
import com.springboot.jdbc.database.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(path = "/insertEmployee",consumes = "application/json")
	public void insertEmployee(@RequestBody Employee insertEmployee) {
		employeeService.insertEmployee(insertEmployee);
	}
	
	@GetMapping(path = "/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getEmployeeById/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") String empId) {
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId), httpHeaders, HttpStatus.OK);
	}
}
