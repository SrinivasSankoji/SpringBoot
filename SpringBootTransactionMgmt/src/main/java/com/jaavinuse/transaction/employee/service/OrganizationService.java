package com.jaavinuse.transaction.employee.service;

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.Employee;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

public interface OrganizationService {

	void joinOrganizationChecked(Employee employee,HealthInsurance healthInsurance) throws InvalidInsuranceAmountException;
	
	void joinOrganizationUnchecked(Employee employee,HealthInsurance healthInsurance);

	public void leaveOrganization(Employee employee,HealthInsurance healthInsurance);
}
