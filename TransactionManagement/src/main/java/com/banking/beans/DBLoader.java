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
	
	}

}
