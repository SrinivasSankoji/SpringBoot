package com.jaavinuse.transaction.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaavinuse.transaction.employee.dao.EmployeeDao;
import com.jaavinuse.transaction.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	@Override
	public void insertEmployees(List<Employee> employees) {
		employeeDao.insertEmployees(employees);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		return employees;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Employee employee = employeeDao.getEmployeeById(empId);
		return employee;
	}

	@Override
	public void deleteEmployeeById(int empId) {
		employeeDao.deleteEmployeeById(empId);
	}

}
