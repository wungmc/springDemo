package com.wung.springdemo.controller;

import com.wung.springdemo.bean.UserBean;
import com.wung.springdemo.dao.UserDAO;
import com.wung.springdemo.service.UserService;
import com.wung.springdemo.util.JSONService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    
    public static Logger log = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
    	log.info("login:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();

        String loginName = request.getParameter("loginname");
        String password = request.getParameter("password");
        log.info("loginName = " + loginName + ", password = " + password);
        if (loginName == null || loginName.isEmpty()) {
            obj.put("result", "error");
            JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
            return;
        }
        if (password == null || password.isEmpty()) {
            obj.put("result", "error");
            JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
            return;
        }

        int count = userService.login(loginName, password);
        if (count < 1) {
            obj.put("result", "fail");
            JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
            return;
        }

        UserBean user = userService.queryUser(loginName);
        log.info(user);
        request.getSession().setAttribute("user", user);

        obj.put("result", "true");
        obj.put("user", user);
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");

    }

    @RequestMapping(value = "/info")
    public void info(HttpServletRequest request, HttpServletResponse response) {
        log.info("info:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();


        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        log.info(user);

        obj.put("result", "true");
        obj.put("user", user);
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");

    }

    @RequestMapping(value = "/saveuser")
    public void saveUser(HttpServletRequest request, HttpServletResponse response) {
        log.info("saveUser:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();

        String userName = request.getParameter("userName");
        String age = request.getParameter("age");
        UserBean user = new UserBean();
        user.setLoginName(userName);
        user.setAge(Integer.parseInt(age));
        boolean result = false;
        try {
            result = userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        obj.put("result", result);
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
    }
    
}
