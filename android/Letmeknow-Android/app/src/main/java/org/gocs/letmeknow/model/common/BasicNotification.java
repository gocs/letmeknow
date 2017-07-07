package org.gocs.letmeknow.model.common;

import com.google.gson.annotations.SerializedName;

import org.gocs.letmeknow.model.common.Component.Choice;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class BasicNotification implements Serializable{

    private static final long serialVersionUID = 5955756411160698711L;

    @SerializedName("object_id")
    private String Id;

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("sender_id")
    private String senderId;

    @SerializedName("content")
    private String content;

    @SerializedName("choices")
    private List<Choice> choices;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
