package com.springboot.jdbc.database.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private String empId;
	private String empName;

}
