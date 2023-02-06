package com.openclassrooms.Project6.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "transactions")

public class Transactions {
	
	@Id
	private Long id;
	
	@Column(name= "sender_id", nullable= false)
	private Long senderId;
	
	@Column(name="description", length= 150, nullable = false)
	private String description; 
	
	@Column(name="amount", nullable = false)
	private int amount; 
	
	@Column(name= "receiver_id", nullable= false)
	private Long receiverId; 

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id; 
	}
	public Long getSender_id() {
		return senderId;
	}
	public void setSender_id(Long sender_id){
		this.senderId = sender_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount(){
		return amount; 
	}
	public void setAmount(int amount) {
		this.amount = amount; 
	}
	public Long getReceiver_id() {
		return receiverId;
	}
	public void setReceiver_id( Long receiver_id) {
		this.receiverId = receiver_id; 
	}
	
	@Override
	public String toString() {
		return this.getSender_id() + ", " + this.getDescription() + ", " + this.getAmount() + ", " + this.getReceiver_id();
	}
	
}
