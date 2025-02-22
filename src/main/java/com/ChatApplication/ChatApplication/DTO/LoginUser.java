package com.ChatApplication.ChatApplication.DTO;

public class LoginUser {

    private String email;

    public LoginUser(String email) {
        this.email = email;
    }

    public LoginUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "email='" + email + '\'' +
                '}';
    }
}
