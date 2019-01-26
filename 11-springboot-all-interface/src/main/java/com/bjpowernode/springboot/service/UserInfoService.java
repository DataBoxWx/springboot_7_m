package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.UserInfo;

import java.util.List;

/**
 * ClassName:UserInfoService
 * package:com.bjpowernode.springboot.service
 * Descrption:
 *
 * @Date:2018/7/28 15:01
 * @Author:724班
 */
public interface UserInfoService {

    public int addUserInfo(UserInfo userInfo);

    public int deleteUserInfoById(Integer id);

    public int updateUserInfo(UserInfo userInfo);

    public List<UserInfo> getUserInfoByPage(int startRow, int pageSize);

    public int getUserInfoByTotal();

    public UserInfo getUserInfoById(Integer id);
}