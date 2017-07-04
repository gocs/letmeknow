package org.gocs.letmeknow.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public class User {
    @SerializedName("username")
    String userName;
    @SerializedName("password")
    String password;

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
}
