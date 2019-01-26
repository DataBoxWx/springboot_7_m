package com.bjpowernode.springboot.mapper;

import com.bjpowernode.springboot.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 传多个参数，
     * 1、用map传
     * 2、用对象传
     * 3、用@Param传
     *
     * @param startRow
     * @param pageSize
     * @return
     */
    List<UserInfo> selectUserInfoByPage(@Param("startRow") int startRow, @Param("pageSize") int pageSize);

    int selectUserInfoByTotal();
}