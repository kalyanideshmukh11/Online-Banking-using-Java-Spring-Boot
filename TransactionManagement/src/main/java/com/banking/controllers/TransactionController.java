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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import com.banking.beans.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public double getBalance(long accNum) {
		double balance = -1.00;
		try {
		        balance= jdbcTemplate
		                .queryForObject("select current_balance from account where account_number = " + accNum, Double.class);
		}
		catch (DataAccessException e) {
			return -1;
		}
		        return balance;
		}
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
	 * @throws CloneNotSupportedException 
	   */
	  @PostMapping("/addTransaction")
	  public ResponseEntity createTransaction(@Valid @RequestBody Transaction transaction) throws CloneNotSupportedException {
		  //setting default transaction fields
		  Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
			transaction.setCreateDt(today);
			if(transaction.getCurrency().equals(""))
				transaction.setCurrency("USD");
		String medium = transaction.getTransMedium();
		List<Transaction> transactions = new ArrayList<Transaction>();
		if(medium.equals("Manual")) {
			//admin transaction
			transaction.setCreatedName("Banker");
			double oBalance = this.getBalance(transaction.getAccNum());
			transaction.setCurrentBalance(oBalance);
			if(oBalance == -1.00) {
				transaction.setStatus("Failed");
				transactions.add(transaction);
				transRepo.saveAll(transactions);
				return ResponseEntity.ok().body("User's account number does not exist, transaction failed");
			}
			if(!this.updateBalance(oBalance + transaction.getTransAmt() , transaction.getAccNum())) {
				transaction.setStatus("Failed");
				transactions.add(transaction);
				transRepo.saveAll(transactions);
				return ResponseEntity.ok().body("User's balance could not be updated, transaction failed");
			}
			transaction.setCurrentBalance(oBalance + transaction.getTransAmt());
			transaction.setStatus("Success");
			transactions.add(transaction);
		}
		else if(medium.equals("Online")) {
			//user transaction
			transaction.setTransType("Debit");
			transaction.setTransAmt(-transaction.getTransAmt());  //negative balance shown in transaction for debit
			double oldBalance = this.getBalance(transaction.getAccNum());
			transaction.setCurrentBalance(oldBalance);
			if(oldBalance == -1.00) {
				transaction.setStatus("Failed");
				transactions.add(transaction);
				transRepo.saveAll(transactions);
				return ResponseEntity.ok().body("Payee account number does not exist, transaction failed");
			}
			if(oldBalance >= -transaction.getTransAmt()){
				double newBalance = oldBalance + transaction.getTransAmt(); //adding instead of subtracting because transaction amount is negative
				if(transaction.getBeneficiary() == 0) {	//external payments
					if(!this.updateBalance(newBalance , transaction.getAccNum())) {
						transaction.setStatus("Failed");
						transactions.add(transaction);
						transRepo.saveAll(transactions);
						return ResponseEntity.ok().body("Payee balance could not be updated, transaction failed");
					}
					transaction.setCurrentBalance(newBalance);
					transaction.setStatus("Success");
					transactions.add(transaction);
				}else {				//internal payments
					double oldBenBalance = this.getBalance(transaction.getBeneficiary());
					if(oldBenBalance == -1.00)
						return ResponseEntity.ok().body("Beneficiary account number does not exist, transaction failed");
					if(!this.updateBalance(newBalance , transaction.getAccNum())) {
						transaction.setStatus("Failed");
						transactions.add(transaction);
						transRepo.saveAll(transactions);
						return ResponseEntity.ok().body("Payee balance could not be updated, transaction failed");
					}
					double newBenBalance = oldBenBalance - transaction.getTransAmt();	//subtracting instead of adding because transaction amount is negative
					transaction.setCurrentBalance(newBalance);
					transaction.setStatus("Success");
					transactions.add(transaction);
					Transaction benTransaction = new Transaction();
					setValues(benTransaction, transaction);
					benTransaction.setCurrentBalance(oldBenBalance);
					if(!this.updateBalance(newBenBalance , benTransaction.getAccNum())) {
						benTransaction.setStatus("Failed");
						transactions.add(benTransaction);
						transRepo.saveAll(transactions);
						return ResponseEntity.ok().body("Beneficiary balance could not be updated, transaction failed");
					}
					benTransaction.setCurrentBalance(newBenBalance);
					benTransaction.setStatus("Success");
					benTransaction.setTransAmt(-transaction.getTransAmt());  //turning balance to positive for beneficiary
					transactions.add(benTransaction);
					}
				
			}else {
				transaction.setStatus("Failed");
				transactions.add(transaction);
				transRepo.saveAll(transactions);
				return ResponseEntity.ok().body("Payee does not have sufficient balance, transaction failed");
			}
		}
	    return ResponseEntity.ok().body(transRepo.saveAll(transactions));
	    
	  }
	  
	  private void setValues(Transaction A, Transaction B) {
			A.setAccNum(B.getBeneficiary());
			A.setCreatedName(B.getCreatedName());
			A.setCreateDt(B.getCreateDt());
			A.setCurrency(B.getCurrency());
			A.setTransType("Credit");
			A.setTransDesc(B.getTransDesc());
			A.setStatus(B.getStatus());
			A.setTransId(B.getTransId()+1);
			A.setTransMedium(B.getTransMedium());			
		}

	private boolean updateBalance(double newBalance , long accNum) {
		// TODO Auto-generated method stub
		try {
		jdbcTemplate.update("update account set current_balance = "+newBalance+" where account_number="+accNum);
		}
		catch (DataAccessException e) {
			return false;
		}
		return true;	
	}

}
