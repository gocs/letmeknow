package org.gocs.letmeknow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.gocs.letmeknow.model.component.Choice;
import org.gocs.letmeknow.model.component.NotificationType;
import org.gocs.letmeknow.model.component.Receipt;
import org.gocs.letmeknow.util.exception.ConversionExceptipon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification implements Serializable{

    private static final long serialVersionUID = -7494198463687008568L;

    @JsonProperty("_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("sender_id")
    private String senderId;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("content")
    private String content;

    @JsonProperty("type")
    private String type = "notification";

    @JsonProperty("notification_type")
    private NotificationType notificationType = NotificationType.NORMAL;

    @JsonProperty("channels")
    private List<String> channels;

    @JsonProperty("choices")
    private List<Choice> choiceList = new LinkedList<>();

    @JsonProperty("receipts")
    private Map<String,Receipt> receiptMap = new HashMap<>();

    @JsonIgnore
    private Map<String, Object> document;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getDocument() throws ConversionExceptipon{
        ObjectMapper mapper = new ObjectMapper();
        if(id == null){
            return mapper.convertValue(this, new TypeReference<Map<String,Object>>() {});
        }else if (document != null){
            Map<String,Object> newProperties = mapper.convertValue(this, new TypeReference<Map<String,Object>>() {});
            document.putAll(newProperties);
            return document;
        }else {
            throw new ConversionExceptipon();
        }
    }

    public void setDocument(Map<String, Object> document) {
        this.document = document;
    }
}
