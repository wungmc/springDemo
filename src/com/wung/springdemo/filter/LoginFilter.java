package com.wung.springdemo.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录过滤器
 *
 * Created by wung on 2016/12/15.
 */
public class LoginFilter implements Filter {

    static Logger log = Logger.getLogger(LoginFilter.class);

    private String loginURL;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.loginURL = filterConfig.getInitParameter("loginURL");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("user") == null) {
            log.info("no login!");
            res.sendRedirect(loginURL);
            return;
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
