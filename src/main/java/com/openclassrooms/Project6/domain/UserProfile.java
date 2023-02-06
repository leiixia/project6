package com.openclassrooms.Project6.domain;

public class UserProfile {
	
	private String firstName; 
	private String lastName;
	private String password; 
	private String confirmPassword;
	private String address;
	private String zip; 
	private String city; 
	
	
	public String getFirstName() {
		return firstName; 
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName; 
	}
	
	public String getLastName() {
		return lastName; 
	}
	public void setLastName(String lastName) {
		this.lastName = lastName; 
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password= password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword; 
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword; 
	}
	public String getAddress() {
		return address; 
	}
	public void setAddress(String address) {
		this.address = address; 
	}
	public String getZip() {
		return zip; 
	}
	public void setzip(String zip) {
		this.zip = zip; 
	}
	public String getCity() {
		return city; 
	}
	public void setCity(String city) {
		this.city = city; 
	}
}
