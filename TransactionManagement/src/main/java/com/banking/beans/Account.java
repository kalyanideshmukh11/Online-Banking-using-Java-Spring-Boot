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
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acc_no", nullable = false)
	private long accNum;
	 @Column(name = "email", nullable = false)
	private String custEmail;
	 @Column(name = "name", nullable = false)
	private String custName;
	 
	 @Column(name = "acc_type", nullable = false)
	private String accountType;
	 
	 @Column(name = "balance", nullable = false)
	private double currentBalance;
	 
	 
	 public long getAccountNum() {
		return accNum;
	}

	public void setAccountNum(long acc_no) {
		this.accNum = acc_no;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustName() {
		return custName;
	}

	public void setCreateDt(String custName) {
		this.custName = custName;
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
	

}
