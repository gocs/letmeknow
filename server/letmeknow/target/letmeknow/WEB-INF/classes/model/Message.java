package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by admin on 2017/7/7.
 */
@Entity
public class Message {
    private int messageId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private int senderId;
    private int receiverId;

    @Id
    @Column(name = "message_id", nullable = false)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 45)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "sender_id", nullable = false)
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receiver_id", nullable = false)
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (senderId != message.senderId) return false;
        if (receiverId != message.receiverId) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (createdAt != null ? !createdAt.equals(message.createdAt) : message.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(message.updatedAt) : message.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(message.deletedAt) : message.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + senderId;
        result = 31 * result + receiverId;
        return result;
    }
}
