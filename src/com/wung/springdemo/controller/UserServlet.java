package com.wung.springdemo.controller;

import com.wung.springdemo.service.UserService;
import com.wung.springdemo.util.JSONService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * 不使用 SpringMVC 的注解方式，而是使用 Servlet 的例子
 * 这种需要在 web.xml 中配置 Servlet 及其映射。
 * 调用该 Servlet 时，需要传入一个参数 op_type，根据该参数判断要调用哪个方法。
 *
 * Created by wung on 2016/12/18.
 */
public class UserServlet extends HttpServlet {
    
    public static Logger log = Logger.getLogger(UserServlet.class);
    
    @Autowired
    private UserService userService;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String op_type = request.getParameter("op_type");
        log.info("操作类型 op_type = " + op_type);

        if (op_type == null || op_type.isEmpty()) {
            log.info("op_type is null!");
            return;
        }

        if ("insert".equalsIgnoreCase(op_type)) {
            insert(request, response);
        }
        else if ("update".equalsIgnoreCase(op_type)) {
            update(request, response);
        }

    }

    private void insert(HttpServletRequest request, HttpServletResponse response) {
    	log.info("insert:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();

        //业务逻辑
        //userService.insert();

        obj.put("result", "true");
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();

        //业务逻辑
        //userService.update();

        obj.put("result", "true");
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
    }
    
}
