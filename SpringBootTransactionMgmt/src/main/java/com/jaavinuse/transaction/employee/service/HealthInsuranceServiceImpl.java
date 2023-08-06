package com.jaavinuse.transaction.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaavinuse.transaction.employee.dao.HealthInsuranceDao;
import com.jaavinuse.transaction.employee.exception.InvalidInsuranceAmountException;
import com.jaavinuse.transaction.employee.model.HealthInsurance;

@Service
public class HealthInsuranceServiceImpl implements HealthInsuranceService{

	@Autowired
	HealthInsuranceDao healthInsuranceDao;
	
	@Override
	@Transactional
	public void registerEmployeeHealthInsurance(HealthInsurance healthInsurance) throws InvalidInsuranceAmountException {
		if (healthInsurance.getCoverageAmount() <= 0) {
			throw new InvalidInsuranceAmountException("Coverage Amount Should not be negative");
			}
		healthInsuranceDao.registerEmployeeHealthInsurance(healthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(int empId) {
		healthInsuranceDao.deleteEmployeeHealthInsuranceById(empId);

	}

}
