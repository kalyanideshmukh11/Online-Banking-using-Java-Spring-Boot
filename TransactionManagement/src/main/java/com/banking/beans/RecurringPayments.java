package com.banking.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RecurringPayments")
@EntityListeners(AuditingEntityListener.class)
public class RecurringPayments {
	@Id
	@Column(name = "recur_payment_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long recurPaymentId;
	public long getrecurPaymentId() {
		return recurPaymentId;
	}
	public void setrecurPaymentId(long recurPaymentId) {
		this.recurPaymentId = recurPaymentId;
	}
	public long getAccNum() {
		return accNum;
	}
	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public double getTransAmt() {
		return paymentAmt;
	}
	public void setTransAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(long beneficiary) {
		this.beneficiary = beneficiary;
	}
	public long getRecurFreq() {
		return recur_freq;
	}
	public void setRecurFreq(long recur_freq) {
		this.recur_freq = recur_freq;
	}
	public long getStart_date() {
		return start_date;
	}
	public void setStart_date(long start_date) {
		this.start_date = start_date;
	}
	public long getEnd_date() {
		return end_date;
	}
	public void setEnd_date(long end_date) {
		this.end_date = end_date;
	}

	
	@Column(name = "account_number", nullable = false)
	private long accNum;
	@Column(name = "trans_type", nullable = false)
	private String transType;
	@Column(name = "payment_amount", nullable = false)
	private double paymentAmt;
	@Column(name = "currency", nullable = false)
	private String currency;
	@Column(name = "create_Dt", nullable = false)
	private Date createDt;
	@Column(name = "trans_status", nullable = false)
	private String status;
	@Column(name = "beneficiary")
	private long beneficiary ;
	@Column(name = "start_date")
	private long start_date ;
	@Column(name = "end_date")
	private long end_date ;
	@Column(name = "recur_freq")
	private long recur_freq ;
	

}
