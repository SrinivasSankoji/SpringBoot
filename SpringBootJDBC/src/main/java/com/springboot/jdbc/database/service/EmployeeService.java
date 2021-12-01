package com.springboot.jdbc.database.service;

import java.util.List;

import com.springboot.jdbc.database.model.Employee;

public interface EmployeeService {
	
	void insertEmployee(Employee emp);

	void insertEmployees(List<Employee> employees);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(String empId);
}
