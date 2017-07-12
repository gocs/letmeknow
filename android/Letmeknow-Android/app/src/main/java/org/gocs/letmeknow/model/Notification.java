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

import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {
    @JsonProperty("_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("sender_id")
    private String senderId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("type")
    private String type = "notification";

    @JsonProperty("notification_type")
    private NotificationType notificationType = NotificationType.NORMAL;

    @JsonProperty("choices")
    private List<Choice> choiceList;

    @JsonProperty("receipts")
    private Map<String,Receipt> receiptMap;

    @JsonIgnore
    private Map<String, Object> document;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getDocument() throws ConversionExceptipon{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
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
