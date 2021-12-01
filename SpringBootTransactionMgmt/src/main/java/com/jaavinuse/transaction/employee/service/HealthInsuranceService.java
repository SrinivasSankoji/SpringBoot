package com.jaavinuse.transaction.employee.service;

import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

public interface HealthInsuranceService {

	void registerEmployeeHealthInsurance(HealthInsurance healthInsurance) throws InvalidInsuranceAmountException;

	void deleteEmployeeHealthInsuranceById(String empid);
}
