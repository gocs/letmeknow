package org.gocs.letmeknow.model;

import com.google.gson.annotations.SerializedName;

import org.gocs.letmeknow.model.component.CircleRoleType;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/7/13.
 */

public class CircleBrief implements Serializable{

    private static final long serialVersionUID = 3259352510977190164L;

    @SerializedName("groupId")
    private String groupId;

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("role")
    private CircleRoleType role;

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

    public CircleRoleType getRole() {
        return role;
    }

    public void setRole(CircleRoleType role) {
        this.role = role;
    }
}
