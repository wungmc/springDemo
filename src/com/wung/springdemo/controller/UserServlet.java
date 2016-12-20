package com.wung.springdemo.controller;

import com.wung.springdemo.service.UserService2;
import com.wung.springdemo.util.JSONService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * Servlet 的生命周期归 Servlet 容器管理，和 Spring 不挨着，如果要在 Servlet 中注入一个 Srping 容器中的 Bean,
 * 其中一个方法就是在初始化方法（init()）中获取到 Spring 的容器，然后从中获取到要注入的 Bean。
 *
 * 这种需要在 web.xml 中配置 Servlet 及其映射。
 * 调用该 Servlet 时，需要传入一个参数 op_type，根据该参数判断要调用哪个方法。
 *
 * Created by wung on 2016/12/18.
 */
public class UserServlet extends HttpServlet {
    
    public static Logger log = Logger.getLogger(UserServlet.class);
    
    //@Autowired
    private UserService2 userService2;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        //用这种硬编码的方式来注入 UserService2
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(cfg.getServletContext());
        //必须在 Spring 的配置文件中定义一个名字为 userService2 的 UserService2
        userService2 = (UserService2) context.getBean("userService2");
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
        //userService2.insert();
        System.out.println(userService2);

        obj.put("result", "true");
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update:");
        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();

        //业务逻辑
        //userService2.update();

        obj.put("result", "true");
        JSONService.writeStringIntoResponse(response, jsonp + "(" + obj + ")");
    }
    
}
