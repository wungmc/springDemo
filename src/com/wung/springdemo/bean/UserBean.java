package com.wung.springdemo.bean;

import java.io.Serializable;

/**
 *
 * Created by wung on 2016/12/14.
 */
public class UserBean implements Serializable {
    public static final long serialVersionUID = -1L;

    private String loginName;
    private int age;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
