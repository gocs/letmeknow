package org.gocs.letmeknow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.gocs.letmeknow.model.component.Choice;
import org.gocs.letmeknow.model.component.NotificationType;
import org.gocs.letmeknow.model.component.Receipt;

import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/11/2017.
 */

public class Notification {
    @JsonProperty("id")
    private String id;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("sender_id")
    private String senderId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("notification_type")
    private NotificationType notificationType = NotificationType.NORMAL;

    @JsonProperty("choices")
    private List<Choice> choiceList;

    @JsonProperty("receipts")
    private Map<String,Receipt> receiptMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public Map<String, Receipt> getReceiptMap() {
        return receiptMap;
    }

    public void setReceiptMap(Map<String, Receipt> receiptMap) {
        this.receiptMap = receiptMap;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
