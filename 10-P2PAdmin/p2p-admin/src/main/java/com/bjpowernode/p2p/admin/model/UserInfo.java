package com.bjpowernode.p2p.admin.model;

import java.util.Date;
import java.util.List;

/**
 * 用户信息对象
 * 
 * @author yanglijun
 *
 */
public class UserInfo {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private Date lastLoginTime;
	
	private Integer staffId;
	
	private StaffInfo staffInfo;
	
	private List<PermissionInfo> menuPermissionInfo;
	
	private List<PermissionInfo> buttonPermissionInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public StaffInfo getStaffInfo() {
		return staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}
	
	public List<PermissionInfo> getMenuPermissionInfo() {
		return menuPermissionInfo;
	}

	public void setMenuPermissionInfo(List<PermissionInfo> menuPermissionInfo) {
		this.menuPermissionInfo = menuPermissionInfo;
	}

	public List<PermissionInfo> getButtonPermissionInfo() {
		return buttonPermissionInfo;
	}

	public void setButtonPermissionInfo(List<PermissionInfo> buttonPermissionInfo) {
		this.buttonPermissionInfo = buttonPermissionInfo;
	}
}
