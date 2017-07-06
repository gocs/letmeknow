package org.gocs.letmeknow.model.remote;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2017/6/29.
 */

public class PrivateMessage {

    private String senderUserName;
    private String recieverUserName;
    private Timestamp sendTime;
    private String content;

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "sendTime=" + sendTime +
                '}';
    }

    public PrivateMessage(){
        this.senderUserName="Mr.Default";
        this.recieverUserName="BoyNextDore";
        this.sendTime = new Timestamp(System.currentTimeMillis());
        this.content ="Let me know";
    }

    public String getRecieverUserName() {
        return recieverUserName;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setRecieverUserName(String recieverUserName) {
        this.recieverUserName = recieverUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {

        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

}
