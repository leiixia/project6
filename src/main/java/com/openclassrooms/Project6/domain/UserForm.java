package com.openclassrooms.Project6.domain;

public class UserForm {
	
	private String firstName; 
	private String mail; 
	private String password; 
	private String address; 
	private String city;
	private String zip;
	
	
	public String getFirstName() {
		return firstName; 
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName; 
	}

	
	public String getMail() {
		return mail; 
	}
	public void setMail(String mail) {
		this.mail = mail; 
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password= password;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
