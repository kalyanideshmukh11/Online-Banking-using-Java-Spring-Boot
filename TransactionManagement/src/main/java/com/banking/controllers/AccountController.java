package com.banking.controllers;

import java.util.List;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.beans.*;

@RestController
@RequestMapping("/api/v1")

public class AccountController {
	@Autowired
	private AccountRepository accountRepo;
	
	 @GetMapping("/accounts")
	  public List<Account> getAllAccounts() {
	    return (List<Account>) accountRepo.findAll();
	  }
	 
	 /**
	   * Gets transactions by id.
	   *
	   * @param userId the user id
	   * @return the users by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/account/{account_number}")
	  public ResponseEntity<Account> getUsersById(@PathVariable(value = "account_number") Long account_number)
	      throws ResourceAccessException {
	    Account account =
	        accountRepo
	            .findById(account_number)
	            .orElseThrow(null);
	    return ResponseEntity.ok().body(account);
	  }
	  
	  /**
	   * Create user user.
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/addAccount")
	  public Account createAccount(@Valid @RequestBody Account account) {
		  return accountRepo.save(account);
	  }
}
