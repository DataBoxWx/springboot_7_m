package com.bjpowernode.multidb.service;

import com.bjpowernode.multidb.model.UserInfo;

/**
 * ClassName:UserInfoService
 * package:com.bjpowernode.multidb.service
 * Descrption:
 *
 * @Date:2018/8/6 17:11
 * @Author:724班
 */
public interface UserInfoService {

    public UserInfo getUserInfoById(Integer id);

    public int updateUserInfo(UserInfo userInfo);
}
