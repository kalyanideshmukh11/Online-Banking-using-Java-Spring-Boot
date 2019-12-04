package com.banking.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
	            .orElseThrow(null);
	    return ResponseEntity.ok().body(transaction);
	  }
	  
	  // View or search transactions using specific transaction type
	  @GetMapping("/transactionsByDt")
	  public ResponseEntity getTransactionsByDt(@RequestParam(name = "accNum") String accNum,@RequestParam(name="createDt")
	  String createDt, @RequestParam(name="minDt") String minDt,
	  @RequestParam(name="credit") String credit,
	  @RequestParam(name="debit") String debit,
	  @RequestParam(name="fees") String fees,
	  @RequestParam(name="checks") String checks
	  )
	      throws ResourceAccessException {
		  Long accNumber;
		  LocalDate toDate = null;
		  LocalDate fromDate = null;
		  List<Transaction> filteredBydt = new ArrayList<Transaction>();
		  List<Transaction> filteredByType ;
		  try
		  {
			  accNumber = Long.parseLong(accNum);
			  final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  
			  if(createDt.isEmpty())
			  {
				 toDate= java.time.LocalDate.now();
			  }
			  else
			  {
				toDate =  LocalDate.parse(createDt,dtf);
			  }
			  if(minDt.isEmpty())
			  {
				  fromDate = toDate.minusMonths(18);
			  }
			  else
			  {
				  fromDate =  LocalDate.parse(minDt,dtf);
			  }
			  filteredBydt = transRepo.findByDt(accNumber,java.sql.Date.valueOf(toDate), java.sql.Date.valueOf(fromDate));
			  
			  List<String> types = new ArrayList<String>();
			  if(credit.equalsIgnoreCase("Y"))
			  {
				  types.add("credit");
			  }
			   if(debit.equalsIgnoreCase("Y"))
			  {
				  types.add("debit");
			  }
			   if(fees.equalsIgnoreCase("Y"))
			  {
				  types.add("fees");
			  }
			   if(checks.equalsIgnoreCase("Y"))
			  {
				  types.add("checks");
			  }
			  if(types.isEmpty() && filteredBydt != null )
			  {
				  return ResponseEntity.ok().body(filteredBydt);
			  }
				  
			  
			   filteredByType = filteredBydt.stream()
					   .filter(transaction -> types.contains(transaction.getTransType().toLowerCase()))
					   .collect(Collectors.toList());
			 if(filteredBydt.isEmpty() || filteredByType.isEmpty())
			 {
				 
					 return ResponseEntity.ok().body("Account number or transactions for the given account number does not exist.");
				
			 }
		  }catch (NumberFormatException e) {
				 return ResponseEntity.ok().body("Only numeric values accepted for Account Number field.");
			}catch (DateTimeParseException e)
		  {
				return ResponseEntity.ok().body("Please enter date in yyyy-mm-dd format.");
		  }	  
		  return ResponseEntity.ok().body(filteredByType);
 
	  }
	  
	  /**
	   * Create user user.
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/addTransaction")
	  public List<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
		String medium = transaction.getTransMedium(); 
		List<Transaction> transactions = new ArrayList<Transaction>();
		if(medium.equals("Manual")) {
			//admin transaction
		}
		else if(medium.equals("Online")) {
			//user transaction
			transaction.setStatus("Success");
		}
		Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
		transaction.setCreateDt(today);
		if(transaction.getCurrency().equals(""))
			transaction.setCurrency("Dollars");
	    return transRepo.saveAll(transactions);
	    
	  }

}
