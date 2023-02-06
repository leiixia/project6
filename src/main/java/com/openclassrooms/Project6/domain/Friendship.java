package com.openclassrooms.Project6.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "friendship")

public class Friendship {
	
	@Id
	private Long id;
	
	@Column(name = "firstfriend_id", nullable = false)
	private int firstfriendId; 
	
	@Column(name = "secondfriend_id", nullable = false)
	private int secondfriendId; 
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id= id; 
	}
	public int getFirstfriend_id() {
		return firstfriendId;
	}
	public void setFirstfriend_id(int firstfriend_id) {
		this.firstfriendId= firstfriend_id;
	}
	public int getSecondfriend_id() {
		return secondfriendId;
	}
	public void setSecondfriend_id(int secondfriend_id) {
		this.secondfriendId= secondfriend_id;
	}
	

	
	@Override
	public String toString() {
		return this.getFirstfriend_id() + ", " + this.getSecondfriend_id();
	}
}
