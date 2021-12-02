package com.springboot.netsurfingzone.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int bookId;
	private String bookName;
	private String bookPrice;

}
