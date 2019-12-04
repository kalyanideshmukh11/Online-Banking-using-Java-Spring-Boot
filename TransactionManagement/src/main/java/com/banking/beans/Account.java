package com.banking.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name="seq", initialValue=1103000000, allocationSize=100)
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(name = "account_number", nullable = false)
	private long account_number;
	 @Column(name = "customer_email", nullable = false)
	private String customer_email;
	 @Column(name = "customer_fname", nullable = false)
	private String customer_fname;
	@Column(name = "customer_lname", nullable = false)
	private String customer_lname;
	@Column(name = "customer_address", nullable = false)
	private String customer_address;
	@Column(name = "customer_phone", nullable = false)
	private long customer_phone;
	 @Column(name = "account_type", nullable = false)
	private String account_type;
	@Column(name = "account_status", nullable = false)
	private String account_status;
	 @Column(name = "current_balance", nullable = false)
	private double current_balance;
	@Column(name = "user_type", nullable = false)
	private String user_type;
	@Column(name = "branch_name", nullable = false)
	private String branch_name;
	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_fname() {
		return customer_fname;
	}

	public void setCustomer_fname(String customer_fname) {
		this.customer_fname = customer_fname;
	}

	public String getCustomer_lname() {
		return customer_lname;
	}

	public void setCustomer_lname(String customer_lname) {
		this.customer_lname = customer_lname;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public long getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(long customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(double current_balance) {
		this.current_balance = current_balance;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
  
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
   
}
