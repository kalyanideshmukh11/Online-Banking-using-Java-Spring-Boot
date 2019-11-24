package com.banking.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DBLoader implements ApplicationRunner {

	private final TransactionRepository transactions ;
	
	@Autowired
	public DBLoader(TransactionRepository transactions)
	{
		this.transactions= transactions;
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		Transaction transaction = new Transaction(00001,1,"Credit","Online","Refund settlement "
				,20.00,"USD",sqlDate,40.00,"Successful");
		transactions.save(transaction);
	}

}
