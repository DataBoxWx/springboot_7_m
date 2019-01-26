package com.bjpowernode.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.springboot.mapper.UserInfoMapper;
import com.bjpowernode.springboot.model.UserInfo;
import com.bjpowernode.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:UserInfoServiceImpl
 * package:com.bjpowernode.springboot.service.impl
 * Descrption:
 *
 * @Date:2018/7/28 15:06
 * @Author:724班
 */
@Component
@Service(timeout = 1000) //dubbo的注解
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public int deleteUserInfoById(Integer id) {
        int delete = userInfoMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            //把缓存也删除
            redisTemplate.delete(id);
        }
        return delete;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        int update  = userInfoMapper.updateByPrimaryKeySelective(userInfo);

        if (update > 0) {
            //把缓存更新一下
            //userInfo = userInfoMapper.selectByPrimaryKey(userInfo.getId());
            redisTemplate.opsForValue().set(userInfo.getId(), userInfo);
        }
        return update;
    }

    @Override
    public List<UserInfo> getUserInfoByPage(int startRow, int pageSize) {
        return userInfoMapper.selectUserInfoByPage(startRow, pageSize);
    }

    @Override
    public int getUserInfoByTotal() {
        return userInfoMapper.selectUserInfoByTotal();
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {

        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(id);

        if (null == userInfo) {

            synchronized (this) {

                userInfo = (UserInfo) redisTemplate.opsForValue().get(id);

                if (null == userInfo) {
                    userInfo = userInfoMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set(id, userInfo);
                }
            }

        }
        return userInfo;
    }
}