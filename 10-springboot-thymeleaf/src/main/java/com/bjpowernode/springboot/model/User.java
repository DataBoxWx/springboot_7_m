package com.bjpowernode.springboot.model;

/**
 * ClassName:User
 * package:com.bjpowernode.springboot.model
 * Descrption:
 *
 * @Date:2018/7/27 15:28
 * @Author:724班
 */
public class User {

    private Integer id;

    private String nick;

    private String phone;

    private String email;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
