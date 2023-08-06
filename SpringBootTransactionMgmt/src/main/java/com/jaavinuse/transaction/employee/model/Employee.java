package com.jaavinuse.transaction.employee.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int empId;
	private String empName;

}
