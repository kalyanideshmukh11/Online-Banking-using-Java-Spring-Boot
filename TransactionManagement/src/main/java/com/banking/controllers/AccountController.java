package com.banking.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	  @GetMapping("/closeAccount/{user_type}/{account_number}")
	  public ResponseEntity closeaccountById(@PathVariable(value = "user_type") String user_type,@PathVariable(value = "account_number") Long account_number)
	      throws ResourceAccessException {
		  Account account =
			        accountRepo
			            .findById(account_number)
			            .orElseThrow(null);
	    if (user_type.equalsIgnoreCase("banker"))
	    {
	    if(account.getAccount_status().equals("inactive"))
	    {
	    	return ResponseEntity.ok().body("This is an inactive account.");
	    }
	    else if(account.getCurrent_balance()>0 )
	    {
	    	return ResponseEntity.ok().body("Current Balance is greater than zero.Please remove your balance and try again.");
	    }
	    else if(account.getCurrent_balance()<0 )
	    {
	    	return ResponseEntity.ok().body("Current Balance is less than zero.Please make balance zero and try again.");
	    }
	    else 
	    account.setAccount_status("inactive");
	    accountRepo.save(account);
	    return ResponseEntity.ok().body(account);
	  }
	    else
	    {
	    	return ResponseEntity.ok().body("Only Banker is authorized to close account.");
	    	}
	    }
	  /**
	   * Create user user.
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/addAccount")
	  public ResponseEntity createAccount(@Valid @RequestBody Account account) {
		  if (account.getUser_type().equalsIgnoreCase("banker")) 
		  {
			  if (account.getAccount_status().equalsIgnoreCase("inactive"))
			  {
				  return ResponseEntity.ok().body("Account status can not be inactive"); 
			  }
			  else
			  {
				  if (account.getAccount_status().equalsIgnoreCase("inactive") ||
						  account.getAccount_status().equalsIgnoreCase("active"))
				  {	
					  if(account.getAccount_type().equalsIgnoreCase("savings") ||
							  account.getAccount_type().equalsIgnoreCase("checking"))
					  {
						  if(account.getBranch_name().equalsIgnoreCase("SF") ||
								  account.getBranch_name().equalsIgnoreCase("SJ") ||
								  account.getBranch_name().equalsIgnoreCase("SD"))
						  {
							  if(Long.toString(account.getCustomer_phone()).length()==10)
							  {
								 if (account.getCurrent_balance()>99)
								 {
									 if(account.getCustomer_fname().isEmpty())
									 {
										 return ResponseEntity.ok().body("Invalid first name");
									 }
									 else if (account.getCustomer_lname().isEmpty())
									 {
										 return ResponseEntity.ok().body("Invalid last name");
									 }
									 else if(account.getCustomer_address().isEmpty())
									 {
										 return ResponseEntity.ok().body("Invalid address."); 
									 }
									 else if(account.getCustomer_email().isEmpty())
									 {
										 return ResponseEntity.ok().body("Invalid email address."); 
									 }
									 else
									 {
								  accountRepo.save(account);
								  return ResponseEntity.ok().body(account);
									 }
							  }
								 else
								 {
									 return ResponseEntity.ok().body("Insufficient balance.\nTo add bank account minimum balance should be $100.");
									 }
								 }
							  else
							  {
								  return ResponseEntity.ok().body("Invalid Phone Number.");  
							  }
					  }
						  else
						  {
							  return ResponseEntity.ok().body("Invalid Branch Name.\nIt can be either of SJ, SD, SF.");   
						  }
					  }		  
					  else
					  {
						  return ResponseEntity.ok().body("Invalid Account Type.\nIt should be either savings or checking.");  
					  }
			  }
				  else
				  {
					  return ResponseEntity.ok().body("Invalid Account Status.\nIt should be either active or inactive");
					  }
				  }
		  }
		  else 
		  {
			  return ResponseEntity.ok().body("Customer is not authorized to add account.");
		  }
		  
	  }
}