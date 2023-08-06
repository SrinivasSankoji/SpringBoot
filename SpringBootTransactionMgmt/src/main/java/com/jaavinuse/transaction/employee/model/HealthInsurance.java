package com.jaavinuse.transaction.employee.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class HealthInsurance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int empId;
	private String healthInsuranceSchemeName;
	private int coverageAmount;

}
