package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserServiceImpl
 * package:com.bjpowernode.springboot.service.impl
 * Descrption:
 *
 * @Date:2018/7/23 17:02
 * @Author:724班
 */
@Service
public class UserServiceImpl implements UserService {

    //注入底层的DAO (省略)

    @Override
    public String syaHi(String name) {
        return "Hi, " + name;
    }
}