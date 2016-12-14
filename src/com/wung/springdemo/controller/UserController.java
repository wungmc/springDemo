package com.wung.springdemo.controller;

import com.wung.springdemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    
    public static Logger log = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/info")
    public void userInfo(HttpServletRequest request, HttpServletResponse response) {
    	log.info("userInfo:");
    	System.out.println(userService);
    }
    
}
