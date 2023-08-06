package com.jaavinuse.transaction.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jaavinuse.transaction.employee.model.HealthInsurance;

@Repository
public class HealthInsuranceDaoImpl implements HealthInsuranceDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void registerEmployeeHealthInsurance(HealthInsurance healthInsurance) {
		String sql = "INSERT INTO HEALTHINSURANCE "
				+ "(empId, healthInsuranceSchemeName, coverageAmount) VALUES (?, ?,?)";
		jdbcTemplate.update(sql, new Object[] { healthInsurance.getEmpId(),
				healthInsurance.getHealthInsuranceSchemeName(), healthInsurance.getCoverageAmount() });
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(int empid) {
		String sql = "DELETE FROM employeeHealthInsurance WHERE empId = ?";
		jdbcTemplate.update(sql, new Object[] { empid });
	}

}
