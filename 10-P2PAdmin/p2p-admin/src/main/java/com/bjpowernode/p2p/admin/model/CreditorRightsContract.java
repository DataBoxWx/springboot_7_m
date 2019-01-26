package com.bjpowernode.p2p.admin.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 债权合同信息对象
 * 
 * @author yanglijun
 *
 */
public class CreditorRightsContract {
	
	private Integer id;
	
    private String applyNo;
    
    private String applyPurpose;
    
    private BigDecimal auditLoanMoney;
    
    private Integer auditLoanTerm;
    
    private String borrowerRealname;
    
    private String borrowerIdcard;
    
    private String borrowerSex;
    
    private String borrowerPresentAddress;
    
    private Date collectFinishTime;
    
    private List<BidInfo> bidInfoList;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getApplyPurpose() {
		return applyPurpose;
	}

	public void setApplyPurpose(String applyPurpose) {
		this.applyPurpose = applyPurpose;
	}

	public BigDecimal getAuditLoanMoney() {
		return auditLoanMoney;
	}

	public void setAuditLoanMoney(BigDecimal auditLoanMoney) {
		this.auditLoanMoney = auditLoanMoney;
	}

	public Integer getAuditLoanTerm() {
		return auditLoanTerm;
	}

	public void setAuditLoanTerm(Integer auditLoanTerm) {
		this.auditLoanTerm = auditLoanTerm;
	}

	public String getBorrowerRealname() {
		return borrowerRealname;
	}

	public void setBorrowerRealname(String borrowerRealname) {
		this.borrowerRealname = borrowerRealname;
	}

	public String getBorrowerIdcard() {
		return borrowerIdcard;
	}

	public void setBorrowerIdcard(String borrowerIdcard) {
		this.borrowerIdcard = borrowerIdcard;
	}

	public String getBorrowerSex() {
		return borrowerSex;
	}

	public void setBorrowerSex(String borrowerSex) {
		this.borrowerSex = borrowerSex;
	}

	public String getBorrowerPresentAddress() {
		return borrowerPresentAddress;
	}

	public void setBorrowerPresentAddress(String borrowerPresentAddress) {
		this.borrowerPresentAddress = borrowerPresentAddress;
	}

	public Date getCollectFinishTime() {
		return collectFinishTime;
	}

	public void setCollectFinishTime(Date collectFinishTime) {
		this.collectFinishTime = collectFinishTime;
	}

	public List<BidInfo> getBidInfoList() {
		return bidInfoList;
	}

	public void setBidInfoList(List<BidInfo> bidInfoList) {
		this.bidInfoList = bidInfoList;
	}
}