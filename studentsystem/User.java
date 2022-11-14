package com.studentsystem;

public class User {
    private String userName;
    private String password;
    private String id;
    private String mobile;

    public User() {
    }

    public User(String userName, String password, String id, String mobile) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
