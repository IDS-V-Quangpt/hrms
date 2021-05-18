package com.hr.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hr.dto.request.EmployeeResponse;
import com.hr.service.EmployeeService;
 
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
    @Autowired
    @Qualifier("messageResource")
    private MessageSource messageSource;
	
    @GetMapping(value = "")
    public String getEmp(Model model) {
        return "employee/listEmployee";
    }
    
    @PostMapping("/add-emp")
    public String addEmp(Model model, @Valid @ModelAttribute EmployeeResponse response, RedirectAttributes redirAttrs) {
    	
    	employeeService.save(response);
    	redirAttrs.addFlashAttribute("message", messageSource.getMessage("message.success.add.employee", null, Locale.US));
    	return "redirect:/employee";
    }
    
    @GetMapping("/list-emp")
    public List<?> getListEmp() {
    	return null;
    }

}