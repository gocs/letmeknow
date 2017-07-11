package org.gocs.letmeknow.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/6/2017.
 */

public class User implements Serializable{

    private static final long serialVersionUID = -1421969003346399347L;

    @SerializedName("userId")
    private String userId;

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("phone_num")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("avatar")
    private String avatarUrl;

    private boolean isLogin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
