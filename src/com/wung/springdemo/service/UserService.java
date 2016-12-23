package com.wung.springdemo.service;

import com.wung.springdemo.bean.UserBean;
import com.wung.springdemo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public int login(String loginName, String password) {
		return userDAO.login(loginName, password);
	}

	public UserBean queryUser(String loginName) {
		return userDAO.queryUser(loginName);
	}

    /**
     * 给该方法加上事务管理，当遇到异常时回滚
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Qualifier("mysqlTx")
    public boolean saveUser(UserBean user) {
        return userDAO.saveUser(user);
    }

}
