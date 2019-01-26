package com.bjpowernode.p2p.admin.model;

import java.math.BigDecimal;

/**
 * 用户及投资信息对象
 *
 * @author yanglijun
 */
public class BidInfo {

	private Integer userId;

    private String phone;
    
    private String name;
    
    private String idCard;
    
    private Integer bidId;
    
    private BigDecimal bidMoney;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public BigDecimal getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(BigDecimal bidMoney) {
		this.bidMoney = bidMoney;
	}
}
