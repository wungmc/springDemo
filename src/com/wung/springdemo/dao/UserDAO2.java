package com.wung.springdemo.dao;

import com.wung.springdemo.bean.UserBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试非注解式注入
 */
public class UserDAO2 {
	
	static Logger log = Logger.getLogger(UserDAO2.class);
    
	@Autowired
	public JdbcTemplate jdbcTemplate_mysql;
	
	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(UserBean user) {
        String sql = "insert into t_user (loginname, age) values (?, ?) ";
        int count = jdbcTemplate_mysql.update(sql, user.getLoginName(), user.getAge());
        return count > 0;
	}

}
