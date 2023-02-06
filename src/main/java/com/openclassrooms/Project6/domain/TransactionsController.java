/*package com.openclassrooms.Project6.domain;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionsController {
	
	@Autowired
	private TransactionsRepository transactionsRepository; 
	
	private static final String[] SENDER_ID = new String[] {"1", "3", "2"};
	
	@ResponseBody
	@RequestMapping("/")
	public String home() {
		String html= "";
		html += "<ul>";
		html += "<li><a href= '/testInsert'>Test Insert</a></li> ";
		html += "<li><a href= '/showAllTransactions'> Show all Transactions</a></li>";
		html += "<li><a href = '/showSender_idTransactions'> Show Senderid Transactions</a></li>";
		html += "<li><a href = '/deleteAllTransactions'> Delete All Transactions </a></li>";
		html += "<ul>";
		return html; 
								
	}
	
	@ResponseBody
	@RequestMapping("/testInsert")
	public String testInsert() {
		Long transMax = this.transactionsRepository.getMaxid();
		
		Transactions transactions = new Transactions();
		int random = new Random().nextInt(3);
		
		long id = transMax + 1; 
		String sender_id = SENDER_ID[random] + "" + id; 
		
		transactions.setId(id);
		
		
		
		
	}
	

} */
