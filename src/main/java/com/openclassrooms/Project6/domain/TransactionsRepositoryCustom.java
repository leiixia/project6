package com.openclassrooms.Project6.domain;

public interface TransactionsRepositoryCustom {
	
	public Long getMaxTransactionsId();
	
	public long updateTransactions(Long transactionId, Long sender_id, String description, int amount, Long receiver_id);

}
