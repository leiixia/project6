package com.openclassrooms.Project6.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "user")
public class User {
	
	@Id
	private long id;
	
	@Column(name= "name", length = 100, nullable= false)
	private String name; 

	@Column(name ="mail", length= 100, nullable= false)
	private String mail;
	
	@Column(name= "password",length= 100, nullable = false)
	private String password; 
	
	@Column(name= "amount", length= 100, nullable = false)
	private int amount; 
	
	@Column(name= "adress", length= 100, nullable= false)
	private String adress;
	
	@Column(name = "city", length= 100, nullable= false)
	private String city;
	
	@Column(name= "zip", length= 100, nullable= false)
	private String zip;
	
	@Column(nullable = false)
	private Boolean admin;
	
	
	
	public Long getId() {
		return id; 
	}
	public void setId(Long id) {
		this.id = id; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name; 
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail= mail; 
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password; 
	}
	
	public int getAmount() {
		return amount; 
	}
	public void setAmount(int amount) {
		this.amount = amount; 
	}
	
	
	public String getAdress() {
		return adress; 
	}
	public void setAdress(String adress) {
		this.adress = adress; 
	}
	public String getZip() {
		return zip; 
	}
	public void setZip(String zip) {
		this.zip = zip; 
	}
	public String getCity() {
		return city; 
	}
	public void setCity(String city) {
		this.city = city; 
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	
	@Override
	public String toString() {
		return this.getName() + ", " + this.getMail() + ", " + this.getPassword(); 
	}

	
	public User() {
		
	}
		public User(String mail, String password) {
			this.mail = mail; 
			this.password = password; 	
		}


	}
