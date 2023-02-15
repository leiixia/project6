package com.openclassrooms.Project6.domain;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BankDAO {
	
	 @Autowired
		private BankRepository bankRepository;
	 
	 	@Transactional
	 	public int getAmount() {
	 		Iterable<Bank> findAll = bankRepository.findAll();	
	 		Iterator<Bank> iterator = findAll.iterator();
	 		if( iterator.hasNext()) {
	 			return iterator.next().getAmount();
	 		}
	 		return 0; 
	 	}
	 	
	 	@Transactional
	 	public void addAmount(int amount) {
	 		Bank bank = new Bank();
	 		bank.setAmount(0);
	 		bank.setId(1L);
	 		Iterable<Bank> findAll = bankRepository.findAll();	
	 		Iterator<Bank> iterator = findAll.iterator();
	 		if( iterator.hasNext()) {
	 			bank = iterator.next();
	 		}
	 		bank.setAmount(bank.getAmount() + amount);
	 		bankRepository.save(bank);
	 	}
	 		
	 	

}
