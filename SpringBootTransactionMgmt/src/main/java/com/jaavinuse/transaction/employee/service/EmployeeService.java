package com.jaavinuse.transaction.employee.service;

import java.util.List;

import com.jaavinuse.transaction.employee.model.Employee;

public interface EmployeeService {
	
	void insertEmployee(Employee emp);

	void insertEmployees(List<Employee> employees);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int empId);
	
	void deleteEmployeeById(int empId);
}
