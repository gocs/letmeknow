package org.gocs.letmeknow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2017/7/13.
 */

public class Member {

    @SerializedName("userId")
    private String userId;

    @SerializedName("username")
    private String userName;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("installationId")
    private String installationId;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }
}
