package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hr.dao.CheckInOutDAO;
import com.hr.dto.response.CheckOutMSResponse;
 
@Controller
public class MainController {
 
    @Autowired
    private CheckInOutDAO checkInOutDAO;
    
//    @Autowired
//    private WorkTimeRepository repo;
 
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String homePage(Model model) {
 
        List<CheckOutMSResponse> checkOut = checkInOutDAO.getListCheckInOut();
        model.addAttribute("checkOut", checkOut);
 
        return "dashboard";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dashboard(Model model) {
 
//    	List<WorkTime> list = repo.findAll();
    	
        return "dashboard";
    }

}