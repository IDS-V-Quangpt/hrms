package com.hr.service;

import java.util.List;

import com.hr.dto.request.EmployeeResponse;
import com.hr.entities.mysql.Employee;

public interface EmployeeService {

	void save(EmployeeResponse response);

	List<Employee> findAll();
}
