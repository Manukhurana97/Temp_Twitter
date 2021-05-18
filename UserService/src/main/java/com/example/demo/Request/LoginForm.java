package com.example.demo.Request;

public class LoginForm {

    public String Username;
    public String password;
    public String twoStepVerificationToken;

    public LoginForm() {

    }


    public LoginForm(String Username, String password) {
        super();
        this.Username = Username;
        this.password = password;
    }

    public String getTwoStepVerificationToken() {
        return twoStepVerificationToken;
    }

    public void setTwoStepVerificationToken(String twoStepVerificationToken) {
        this.twoStepVerificationToken = twoStepVerificationToken;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
