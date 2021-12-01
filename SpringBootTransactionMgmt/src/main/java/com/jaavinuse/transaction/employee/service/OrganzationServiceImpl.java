package com.jaavinuse.transaction.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.Employee;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

@Service
@Transactional
public class OrganzationServiceImpl implements OrganizationService{

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HealthInsuranceService healthInsuranceService;
	
	@Override
	@Transactional(rollbackFor = InvalidInsuranceAmountException.class)
	public void joinOrganization(Employee employee, HealthInsurance healthInsurance) throws InvalidInsuranceAmountException
	{
		employeeService.insertEmployee(employee);
		//For Unchecked Exception and @Transactioal Annotation works
		/**if (employee.getEmpId().equals("emp1")) {
			throw new RuntimeException("thowing exception to test transaction rollback");
		}**/
		try {
			healthInsuranceService.registerEmployeeHealthInsurance(healthInsurance);
			} catch (InvalidInsuranceAmountException e) {
			throw new InvalidInsuranceAmountException("Exception is thrown");
			}
	}

	@Override
	//@Transactional
	public void leaveOrganization(Employee employee, HealthInsurance healthInsurance) {
		employeeService.deleteEmployeeById(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(healthInsurance.getEmpId());
	}

}
