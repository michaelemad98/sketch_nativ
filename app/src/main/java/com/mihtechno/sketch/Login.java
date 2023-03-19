package com.mihtechno.sketch;

public class Login {
    private String UserName;
    private String Password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Login(String userName, String password) {
        this.UserName = userName;
        this.Password = password;
    }
}

