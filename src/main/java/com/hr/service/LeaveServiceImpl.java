package com.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.dao.repository.LeaveRepository;
import com.hr.entities.mysql.Leave;

@Service
@Transactional(transactionManager = "transactionManager")
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public void save(Leave leave) {
		leaveRepository.save(leave);
	}

	@Override
	public List<Leave> findAll() {
		return leaveRepository.findAll();
	}
	
}
