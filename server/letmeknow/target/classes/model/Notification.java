package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    private List<String> receiver_id;

    private String sender_id;

    private String content;

    private List<Integer> resultformat;

    private List<NotificationResult> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(List<String> receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getResultformat() {
        return resultformat;
    }

    public void setResultformat(List<Integer> resultformat) {
        this.resultformat = resultformat;
    }

    public List<NotificationResult> getResults() {
        return results;
    }

    public void setResults(List<NotificationResult> results) {
        this.results = results;
    }

    public Notification(List<String> receiver_id, String sender_id, String content, List<Integer> resultformat, List<NotificationResult> results) {
        this.receiver_id = receiver_id;
        this.sender_id = sender_id;
        this.content = content;
        this.resultformat = resultformat;
        this.results = results;
    }
}
