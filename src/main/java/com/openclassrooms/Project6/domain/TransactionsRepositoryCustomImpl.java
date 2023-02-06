package com.openclassrooms.Project6.domain;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionsRepositoryCustomImpl implements TransactionsRepositoryCustom {
	
	@Autowired
	EntityManager entityManager; 
	
	@Override
	public Long getMaxTransactionsId() {
		try {
			String sql= "SELECT coalescence(max(e.id), 0) FROM Transactions e";
			Query query = entityManager.createQuery(sql);
			return(Long) query.getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		}
	}
	

	@Override
	public long updateTransactions(Long transactionsId, Long sender_id, String description, int amount, Long receiver_id) {
		Transactions e = entityManager.find(Transactions.class, transactionsId);
		if(e== null) {
			return 0;
		}
		e.setSender_id(sender_id);
		e.setDescription(description);
		e.setAmount(amount);
		e.setReceiver_id(receiver_id);
		return 1;
	}
}
