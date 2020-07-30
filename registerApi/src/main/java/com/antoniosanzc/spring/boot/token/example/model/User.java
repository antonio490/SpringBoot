package com.antoniosanzc.spring.boot.token.example.model;

public class User {

	private String username;
	private String passsword;
	
	public User(String username, String passsword) {
		super();
		this.username = username;
		this.passsword = passsword;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasssword() {
		return passsword;
	}
	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}
}
