package com.flipkart.bean;

public class Admin extends User{
    private String dateOfJoining;

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Admin(String userName, String name, String role, String password,String dateOfJoining) {
        super(userName,name,role,password);
        this.dateOfJoining = dateOfJoining;
    }
    public Admin() {
        super();
    }
}
