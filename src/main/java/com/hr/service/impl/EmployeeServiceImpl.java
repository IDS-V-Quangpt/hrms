package com.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.dao.repository.EmployeeRepository;
import com.hr.dto.request.EmployeeResponse;
import com.hr.entities.mysql.Employee;
import com.hr.service.EmployeeService;

@Service
@Transactional(transactionManager = "transactionManager")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void save(EmployeeResponse response) {
		
		Employee emp = new Employee();
		emp.setFullName(response.getFullName());
		emp.setMarried(response.getMarried());
		emp.setUserCodeID(response.getUserCodeID());
		
		employeeRepository.save(emp);
	}
	
}
