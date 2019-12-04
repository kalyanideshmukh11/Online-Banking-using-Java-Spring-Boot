package com.banking.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.beans.RecurringPayments;
import com.banking.beans.RecurringPaymentsRepository;

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
	  public RecurringPayments createAccount(@Valid @RequestBody RecurringPayments recurpayments) {
		  return recurPaymentsRepo.save(recurpayments);
	  }

}
