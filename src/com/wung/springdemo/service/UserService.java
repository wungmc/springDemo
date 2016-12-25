package com.wung.springdemo.service;

import com.wung.springdemo.bean.UserBean;
import com.wung.springdemo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
     * 给该方法加上事务管理，当遇到异常时回滚。
     * 问题：在 mysql 上始终不起作用，用的 MySQL Community Server，引擎是 InnoDB。
     *
     * @param user
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRED)
    @Qualifier("mysqlTx")
    public boolean saveUser(UserBean user) {
        boolean b1 = userDAO.saveUser(user);
//        boolean b1 = userDAO.testOracleTx(user);
//        throw new UnsupportedOperationException();
        return b1;
    }

}
