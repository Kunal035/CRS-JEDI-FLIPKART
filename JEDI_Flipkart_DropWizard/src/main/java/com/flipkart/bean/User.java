package com.flipkart.bean;

public class User {


	private String userName;
	private String name;
	private String role;
	private String password;
	private int userId;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String userName, String name, String role, String password, Integer userId) {
		super();
		this.userName = userName;
		this.name = name;
		this.role = role;
		this.password = password;
		this.userId = userId;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}


    public void setAdminId(Integer userId) {

    }
}
