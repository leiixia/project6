package com.openclassrooms.Project6.domain;

public class UserLogin {
	
	private String mail; 
	private String password; 
	private String confirmPassword;
	
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
