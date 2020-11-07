package org.login.models;

public class LoginRequestDTO {

	private String userCredential;

    private String password;

	public String getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(String userCredential) {
		this.userCredential = userCredential;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
