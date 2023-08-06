package com.jaavinuse.transaction.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.Employee;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HealthInsuranceService healthInsuranceService;

	@Override
	@Transactional(rollbackFor = InvalidInsuranceAmountException.class)
	public void joinOrganizationChecked(Employee employee, HealthInsurance healthInsurance)
			throws InvalidInsuranceAmountException {
		try {
			employeeService.insertEmployee(employee);
			healthInsuranceService.registerEmployeeHealthInsurance(healthInsurance);
		} catch (InvalidInsuranceAmountException e) {
			throw new InvalidInsuranceAmountException("Exception is thrown");
		}
	}

	@Override
	@Transactional
	/** For Unchecked Exception @Transactioal Annotation works **/
	public void joinOrganizationUnchecked(Employee employee, HealthInsurance healthInsurance) {
		employeeService.insertEmployee(employee);
		//
		if (employee.getEmpId() == 2) {
			throw new RuntimeException("thowing exception to test transaction rollback");
		}
		try {
			healthInsuranceService.registerEmployeeHealthInsurance(healthInsurance);
		} catch (InvalidInsuranceAmountException e) {
			e.printStackTrace();
		}

	}

	@Override
	@Transactional
	public void leaveOrganization(Employee employee, HealthInsurance healthInsurance) {
		employeeService.deleteEmployeeById(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(healthInsurance.getEmpId());
	}

}
