package com.jaavinuse.transaction.employee.service;

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.Employee;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

public interface OrganizationService {

	public void joinOrganization(Employee employee,HealthInsurance healthInsurance) throws InvalidInsuranceAmountException;;

	public void leaveOrganization(Employee employee,HealthInsurance healthInsurance);
}
