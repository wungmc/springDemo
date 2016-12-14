package com.wung.springdemo.service;

import com.wung.springdemo.bean.UserBean;
import com.wung.springdemo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserBean queryUser(String loginName) {
		return userDAO.queryUser(loginName);
	}
	
}
