package com.hr.service;

import java.util.List;

import com.hr.entities.mysql.Leave;

public interface LeaveService {

	void save(Leave leave);

	List<Leave> findAll();
}
