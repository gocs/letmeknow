package org.gocs.letmeknow.model.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class BasicUser implements Serializable{

    private static final long serialVersionUID = 6259656613422687857L;

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
}
