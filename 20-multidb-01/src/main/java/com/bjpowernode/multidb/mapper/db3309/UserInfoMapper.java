package com.bjpowernode.multidb.mapper.db3309;

import com.bjpowernode.multidb.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository("userInfoMapper3309")
public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}