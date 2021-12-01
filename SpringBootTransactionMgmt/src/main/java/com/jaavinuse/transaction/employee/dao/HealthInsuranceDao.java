package com.jaavinuse.transaction.employee.dao;

import com.jaavinuse.transaction.employee.model.HealthInsurance;

public interface HealthInsuranceDao {

	void registerEmployeeHealthInsurance(HealthInsurance healthInsurance);

	void deleteEmployeeHealthInsuranceById(String empid);

}
