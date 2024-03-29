package com.jaavinuse.transaction.employee.controller;

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

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.Employee;
import com.jaavinuse.transaction.employee.model.HealthInsurance;
import com.jaavinuse.transaction.employee.service.EmployeeService;
import com.jaavinuse.transaction.employee.service.OrganizationService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private OrganizationService organizationService;
	
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
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") int empId) {
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId), httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping(path = "/checkTransactionManagement")
	public ResponseEntity<String> checkTransactionManagement(@RequestBody Employee employee) throws InvalidInsuranceAmountException {
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HealthInsurance healthInsurance = new HealthInsurance();
		healthInsurance.setEmpId(employee.getEmpId());
		healthInsurance.setHealthInsuranceSchemeName("premium");
		healthInsurance.setCoverageAmount(10);
	    organizationService.joinOrganizationUnchecked(employee, healthInsurance);
		return new ResponseEntity<String>("Success", httpHeaders, HttpStatus.OK);
	}
}
