package com.bjpowernode.multidb.service.impl;

import com.bjpowernode.multidb.model.UserInfo;
import com.bjpowernode.multidb.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserInfoServiceImpl
 * package:com.bjpowernode.multidb.service.impl
 * Descrption:
 *
 * @Date:2018/8/6 17:12
 * @Author:724班
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private com.bjpowernode.multidb.mapper.db3307.UserInfoMapper userInfoMapper3307;

    @Autowired
    private com.bjpowernode.multidb.mapper.db3308.UserInfoMapper userInfoMapper3308;

    @Autowired
    private com.bjpowernode.multidb.mapper.db3309.UserInfoMapper userInfoMapper3309;

    @Autowired
    private com.bjpowernode.multidb.mapper.db3310.UserInfoMapper userInfoMapper3310;

    /**
     * 读数据，操作从库 （3309，3310）
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo getUserInfoById(Integer id) {
        return userInfoMapper3310.selectByPrimaryKey(id);
    }

    /**
     * 写数据，操作主库 （3307，3308）
     *
     * @param userInfo
     * @return
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper3307.updateByPrimaryKeySelective(userInfo);
    }
}