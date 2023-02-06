package com.openclassrooms.Project6.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Long>{
	
	List<Bank> findByTransactionId(Long transactionid);

}
