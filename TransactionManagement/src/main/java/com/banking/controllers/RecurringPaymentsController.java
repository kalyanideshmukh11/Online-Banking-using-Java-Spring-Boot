package com.banking.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.beans.RecurringPayments;
import com.banking.beans.RecurringPaymentsRepository;
import com.banking.beans.Transaction;

@RestController
@RequestMapping("/api/v1")
public class RecurringPaymentsController {
	@Autowired
	private RecurringPaymentsRepository recurPaymentsRepo;
	
	/**
	   * Create recurring payment.
	   *
	   * @param recurpayments recurring payment
	   * @return recurring payment
	   */
	  @PostMapping("/addRecurPayment")
	  public RecurringPayments newRecPayment(@Valid @RequestBody RecurringPayments recurpayments) {
		  Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
		  recurpayments.setCreateDt(today);
		  recurpayments.setStatus("scheduled");
		  return recurPaymentsRepo.save(recurpayments);
	  }
	  
	  @GetMapping("/getRecurPayments")
	  public List<RecurringPayments> getRecPayments(@Valid @RequestBody RecurringPayments recurpayments) {
		  return (List<RecurringPayments>) recurPaymentsRepo.findAll();
	  }

}
