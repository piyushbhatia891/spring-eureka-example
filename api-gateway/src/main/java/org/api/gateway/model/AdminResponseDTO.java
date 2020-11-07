package org.api.gateway.model;

import java.util.ArrayList;
import java.util.List;

public class AdminResponseDTO {

	private String emailAddress;
	private String password;
	private List<String> roles=new ArrayList<String>();
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
		
}
