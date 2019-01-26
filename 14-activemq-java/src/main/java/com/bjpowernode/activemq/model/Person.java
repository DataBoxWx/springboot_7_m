package com.bjpowernode.activemq.model;

import java.io.Serializable;

/**
 * ClassName:Person
 * package:com.bjpowernode.activemq.model
 * Descrption:
 *
 * @Date:2018/8/3 14:59
 * @Author:724班
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -5167596584604331988L;

    private String name;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
