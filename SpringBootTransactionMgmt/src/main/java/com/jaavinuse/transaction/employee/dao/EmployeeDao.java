package com.jaavinuse.transaction.employee.dao;

import java.util.List;

import com.jaavinuse.transaction.employee.model.Employee;

public interface EmployeeDao {

	void insertEmployee(Employee emp);

	void insertEmployees(List<Employee> employees);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int empId);
	
	void deleteEmployeeById(int empId);
}
