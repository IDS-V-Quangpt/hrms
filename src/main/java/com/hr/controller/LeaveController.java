package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hr.entities.mysql.Leave;
import com.hr.service.impl.LeaveServiceImpl;

@Controller
@RequestMapping(value = "/leave")
public class LeaveController {

	@Autowired
	private LeaveServiceImpl leaveService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		List<Leave> leaveList = leaveService.findAll();
		model.addAttribute("list", leaveList);
		return "leave/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addLeave(Leave form) {
		return "leave/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAddLeave(Leave leave) {
		leaveService.save(leave);
		return "leave/list";
	}
}