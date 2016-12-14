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

@Repository
public class UserDAO {
	
	static Logger log = Logger.getLogger(UserDAO.class);
    
	@Autowired
	public JdbcTemplate jdbcTemplate_mysql;

	public UserBean queryUser(final String loginName) {
		log.info("queryUser() : " + loginName);
		
		String sql = "SELECT t.loginname, t.age from t_user t where t.loginname = ?";
		try {
			List<UserBean> users = jdbcTemplate_mysql.query(sql, new RowMapper<UserBean>() {
				
				@Override
				public UserBean mapRow(ResultSet rs, int i)
						throws SQLException {
					UserBean user = new UserBean();
					user.setLoginName(loginName);
					user.setAge(rs.getInt("age"));
					return user;
				}
			}, loginName);
			return users.size() > 0 ? users.get(0) : null;
		} catch (Exception e) {
			log.error("queryUser() exception : " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(UserBean user) {
		try {
			String sql = "insert into t_user (loginname, age) values (?, ?) ";
			int count = jdbcTemplate_mysql.update(sql, user.getLoginName(), user.getAge());
			return count > 0;
		} catch (Exception e) {
			log.error("exception :" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 更新用户表
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveOrUpdateUser(final UserBean user) {
		log.error("saveUser begin..." + user);
		try {
			String sql =
                    " merge into t_user tu using " +
                            " (select ? as loginname, " +
                            " ? as age " + " ) tn" +
                            " on (tu.loginname = tn.loginname) " +
                            " when  matched                  then " +
                            " update set " + " tu.age = tu.age " +
                            " WHEN NOT MATCHED THEN " +
                            " insert (tu.loginname, tu.age) " +
                            " values (tn.loginname, tn.age) ";

			int count = jdbcTemplate_mysql.update(sql, user.getLoginName(), user.getAge());
			return count > 0;
		} catch (Exception e) {
			log.error("exception :" + e.getMessage());
			return false;
		}
	}

	public List<UserBean> queryUsersByAge() {
		try {
			String sql = "select t.loginname, t.age from t_user t where t.age = ?";
			return jdbcTemplate_mysql.query(sql, new RowMapper<UserBean>() {
				
				@Override
				public UserBean mapRow(ResultSet rs, int i)
						throws SQLException {
					UserBean bean = new UserBean();
					bean.setLoginName(rs.getString("loginname"));
					bean.setAge(rs.getInt("age"));
					return bean;
				}
			});
		} catch (Exception e) {
			log.error("exception :" + e.getMessage());
			return new ArrayList<UserBean>(1);
		}
	}

    /**
     * 注意 queryForObject() 方法的用法
     * 该方法只能返回单一的值，如果结果多于 1 条，则会抛异常
     *
     * @param loginName 用户登录名
     * @return
     */
	public int queryAgeByloginName(String loginName) {
		String sql = "SELECT u.age from t_user u WHERE u.loginname = ?";
		try {
            return jdbcTemplate_mysql.queryForInt(sql, loginName);
//			return jdbcTemplate_mysql.queryForObject(sql, Integer.class, loginName);
		} catch (Exception e) {
			log.error("exception :" + e.getMessage());
			return 0;
		}
	}

}
