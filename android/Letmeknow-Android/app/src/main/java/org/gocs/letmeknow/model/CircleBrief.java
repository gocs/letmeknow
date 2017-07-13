package org.gocs.letmeknow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2017/7/13.
 */

public class CircleBrief {

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("group_name")
    private String groupName;

    @SerializedName("permission")
    private String permission;

    public String getGroupId() { return groupId; }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPermission() { return permission; }

    public void setPermission(String permission) { this.permission = permission; }
}
