package com.example.sanfe.digitalcampus.logic.data;

/**
 * Created by Marta on 24/05/2016.
 */
public class User {

    private boolean userRememberMe;
    private String userUsername;
    private String userPassword;

    public User(){}

    public User(boolean userRememberMe, String userUsername, String userPassword) {
        this.userPassword = userPassword;
        this.userRememberMe = userRememberMe;
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserRememberMe() {
        return userRememberMe;
    }

    public void setUserRememberMe(boolean userRememberMe) {
        this.userRememberMe = userRememberMe;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
