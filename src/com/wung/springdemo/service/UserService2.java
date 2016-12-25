package com.wung.springdemo.service;

import com.wung.springdemo.bean.UserBean;
import com.wung.springdemo.dao.UserDAO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用于演示在 Servlet 中注入 Spring 中的 Bean.
 * （非注解式）
 */
public class UserService2 {
    @Autowired
    @Qualifier("userDAO2")
    private UserDAO2 userDAO2;

    /**
     * 默认只对 RuntimeException 异常回滚，
     * 如果要对所有异常回滚，则加上：rollbackFor = Exception.class
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Qualifier("mysqlTx")
    public boolean insert() throws Exception {
        UserBean user = new UserBean();
        user.setLoginName("test");
        user.setAge(99);
        userDAO2.saveUser(user);
        throw new UnsupportedOperationException();
//        return true;
    }
}
