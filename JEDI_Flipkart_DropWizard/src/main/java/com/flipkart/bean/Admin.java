package com.flipkart.bean;

public class Admin extends User{
    private String dateOfJoining;

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Admin(String userName, String name, String role, String password,String dateOfJoining, Integer userId) {
        super(userName,name,role,password, userId);
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public void setAdminId(Integer userId) {
        super.setUserId(userId);
    }
    public Integer getAdminId(){
        return super.getUserId();
//        return 0;
    }

    public Admin() {
        super();
    }
}
