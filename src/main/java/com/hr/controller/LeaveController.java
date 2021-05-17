package com.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hr.dao.CheckInOutDAO;
import com.hr.dao.LeaveDAO;
import com.hr.entities.mysql.Leave;

@Controller
@RequestMapping(value = "/leave")
public class LeaveController {

	@Autowired
    private LeaveDAO leaveDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		return "leave/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addLeave(Leave form) {
		return "leave/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAddLeave(Leave form) {
		leaveDAO.save(form);
		return "leave/list";
	}
}