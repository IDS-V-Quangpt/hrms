package com.hr.entities.mysql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "UserCodeID")
	private String userCodeID;
	
	@Column(name = "FullName")
	private String fullName;
	
	@Column(name = "Married")
	private Boolean married;
	
	@Column(name = "Number_Child")
	private int numberChild;
}
