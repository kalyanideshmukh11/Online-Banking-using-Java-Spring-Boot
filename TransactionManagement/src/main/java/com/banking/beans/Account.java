package com.banking.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account {
	
	@Column(name = "cust_id", nullable = false)
	
	private long custId;
	 @Column(name = "cust_name", nullable = false)
	private String custName; // decide whether to keep or not
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accNum;
	 @Column(name = "create_dt", nullable = false)
	private Date createDt;
	 
	 @Column(name = "account_type", nullable = false)
	private String accountType;
	 
	 @Column(name = "current_balance", nullable = false)
	private double currentBalance;
	 
	 @Column(name = "updated_at", nullable = false)
	    @LastModifiedDate
	    private Date updatedAt;
	 
	 
	 public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	

}
