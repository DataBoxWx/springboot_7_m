package com.bjpowernode.p2p.admin.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.p2p.admin.model.UserInfo;

/**
 * 用户相关操作Service接口
 * 
 * @author yanglijun
 *
 */
public interface UserInfoService {
	
	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserInfo login (String userName, String password);
	
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