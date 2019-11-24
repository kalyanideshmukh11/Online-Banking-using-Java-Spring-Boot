package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.banking.controllers.TransactionController;

@SpringBootApplication
@ComponentScan(basePackageClasses=TransactionController.class)
public class Cmpe202TransactionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cmpe202TransactionManagementApplication.class, args);
	}

}
