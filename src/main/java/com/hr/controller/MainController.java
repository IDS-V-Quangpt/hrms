package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hr.dao.AdvertiserDAO;
import com.hr.dao.PublisherDAO;
import com.hr.entities.entity1.Publisher;
import com.hr.entities.entity2.Advertiser;
 
@Controller
public class MainController {
 
    @Autowired
    private PublisherDAO publisherDAO;
 
    @Autowired
    private AdvertiserDAO advertiserDAO;
 
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String homePage(Model model) {
 
        List<Advertiser> advertisers = advertiserDAO.listAdvertisers();
        List<Publisher> publishers = publisherDAO.listPublishers();
 
        model.addAttribute("advertisers", advertisers);
        model.addAttribute("publishers", publishers);
 
        return "home";
    }
 
}