package com.bjpowernode.p2p.admin.model;

/**
 * 组织机构信息对象
 * 
 * @author yanglijun
 *
 */
public class OrgInfo {
	
    private Integer orgId;

    private String orgName;

    private Integer pid;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}