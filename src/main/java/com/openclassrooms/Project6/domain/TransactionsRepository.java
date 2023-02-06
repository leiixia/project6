package com.openclassrooms.Project6.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transactions, Long> {
	
	List<Transactions> findBySenderId(Long sender_id);
	List<Transactions> findByReceiverId(Long receiver_id);
	
	//@Query("SELECT coalesce(max(e.id), 0) FROM Transactions e")
	//Long getMaxid();


}
