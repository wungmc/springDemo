package com.wung.springdemo.controller;

import com.wung.springdemo.util.JSONService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;

/**
 * 登出的功能
 *
 * Servlet 的例子（里面不需要注入的时候）
 * 这种需要在 web.xml 中配置 Servlet 及其映射。
 *
 * Created by wung on 2016/12/18.
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public static Logger log = Logger.getLogger(LogoutServlet.class);
    
    @Override
    public void init() throws ServletException {
    	
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        log.info("logout..");

        String jsonp = request.getParameter("jsonpcallback");
        JSONObject obj = new JSONObject();
        obj.put("result", true);

        HttpSession session = request.getSession();
        if (session != null) {
        	session.invalidate();
        }
        //
        Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setDomain("localhost"); //自己的web系统域名
				cookie.setPath("/");
				response.addCookie(cookie);				
			}
		}

        JSONService.writeStringIntoResponse(response, jsonp + "("+ obj + ")");
	} 
}
