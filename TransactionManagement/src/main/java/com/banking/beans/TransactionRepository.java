package com.banking.beans;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
@Query("FROM Transaction WHERE createDt <= :currentDt AND createDt >= :minDt ORDER BY createDt DESC")
List<Transaction> findByDt(@Param("currentDt")Date currentDt,@Param("minDt") Date minDt);

}
