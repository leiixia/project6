package com.openclassrooms.Project6.domain;


public class Person {
	
	private String firstName;
	private String lastName; 
	private String mail; 
	private String password; 
	private String confirmPassword; 

	
	public Person() {

}
	public Person(String firstName, String lastName, String mail, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName; 
		this.mail = mail;
		this.password = password; 
		this.confirmPassword = confirmPassword; 
	}
	
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
	
	public String getConfirmPassword() {
		return confirmPassword; 
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword; 
	}
}