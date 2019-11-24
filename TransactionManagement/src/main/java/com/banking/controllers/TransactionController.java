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

import com.banking.beans.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	private TransactionRepository transRepo;
	
	 @GetMapping("/transactions")
	  public List<Transaction> getAllTransactions() {
	    return (List<Transaction>) transRepo.findAll();
	  }
	 
	 /**
	   * Gets transactions by id.
	   *
	   * @param userId the user id
	   * @return the users by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/transaction/{accNum}")
	  public ResponseEntity<Transaction> getUsersById(@PathVariable(value = "accNum") Long accNum)
	      throws ResourceAccessException {
	    Transaction transaction =
	        transRepo
	            .findById(accNum)
	            .orElseThrow();
	    return ResponseEntity.ok().body(transaction);
	  }
	  
	  /**
	   * Create user user.
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/addTransaction")
	  public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
	    return transRepo.save(transaction);
	  }

}
