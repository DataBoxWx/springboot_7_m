package com.bjpowernode.p2p.admin.mapper;

import com.bjpowernode.p2p.admin.model.UserInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户相关处理Mapper
 * 
 * @author yanglijun
 *
 */
@Mapper
public interface UserInfoMapper {
	
	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param paramMap
	 * @return
	 */
    public UserInfo selectByUserNameAndPassword(Map<String, Object> paramMap);
    
    /**
	 * 分页查询用户信息数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<UserInfo> getUserInfoByPage(Map<String, Object> paramMap);
	
	/**
	 * 分页查询用户信息总数
	 * 
	 * @param paramMap
	 * @return
	 */
	public int getUserInfoByTotal(Map<String, Object> paramMap);
	
	/**
	 * 添加用户
	 * 
	 * @param userInfo
	 * @return
	 */
	public int addUser (UserInfo userInfo);
	
	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUser (int id);
	
	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById (Integer userId);
}