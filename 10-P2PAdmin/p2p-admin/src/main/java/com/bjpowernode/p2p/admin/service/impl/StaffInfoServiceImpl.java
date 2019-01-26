package com.bjpowernode.p2p.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.p2p.admin.mapper.StaffInfoMapper;
import com.bjpowernode.p2p.admin.model.StaffInfo;
import com.bjpowernode.p2p.admin.service.StaffInfoService;

/**
 * 员工信息处理Service接口实现
 * 
 * @author yanglijun
 *
 */
@Service("staffInfoService")
public class StaffInfoServiceImpl implements StaffInfoService {

	/**注入StaffInfoMapper*/
	@Autowired
	private StaffInfoMapper staffInfoMapper;
	
	/**
	 * 根据id查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public StaffInfo getStaffInfoById (int staffId) {
		return staffInfoMapper.getStaffInfoById(staffId);
	}
	
	/**
	 * 根据输入的手机号开始几位查询匹配的手机号
	 * 
	 * @param startPhone
	 * @return
	 */
	public List<Map<String, Object>> getStaffPhone (String startPhone) {
		return staffInfoMapper.getStaffPhone(startPhone);
	}
	
	/**
	 * 根据phone查询员工信息
	 * 
	 * @param id
	 * @return
	 */
	public StaffInfo getStaffInfoByPhone (String phone) {
		return staffInfoMapper.getStaffInfoByPhone(phone);
	}
}
