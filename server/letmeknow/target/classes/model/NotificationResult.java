package model;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
public class NotificationResult {
    private String receiver;
    private String status;
    private List<String> result;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
