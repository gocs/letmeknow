package couch;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/19.
 */
@Document
public class Notification {
    @Id @GeneratedValue(strategy= GenerationStrategy.UNIQUE)
    private String _id;
    @Field("group_id")
    private String groupId;

    @Field("group_name")
    private String groupName;

    @Field("sender_id")
    private String senderId;

    @Field("sender_name")
    private String senderName;

    @Field("content")
    private String content;

    @Field("type")
    private String type;

    @Field("notification_type")
    private String notificationType;

    @Field("choices")
    private List<Map<String,String>> choiceList;

    public List<Map<String, String>> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Map<String, String>> choiceList) {
        this.choiceList = choiceList;
    }

    @Field("channels")
    private List<String> channels;

    /*
    @Field("choices")
    private List<String> choiceList = new ArrayList<String>();
    */

    @Field("receipts")
    private Map<String,Map<String,String> > receiptMap;

    public Map<String, Map<String, String>> getReceiptMap() {
        return receiptMap;
    }

    public void setReceiptMap(Map<String, Map<String, String>> receiptMap) {
        this.receiptMap = receiptMap;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    /*
    public List<String> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        this.choiceList = choiceList;
    }
    */

    /*
    public Map<String, String> getReceiptMap() {
        return receiptMap;
    }

    public void setReceiptMap(Map<String, String> receiptMap) {
        this.receiptMap = receiptMap;
    }
    */
}
