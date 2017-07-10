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
public class Complaint {
    private int reportId;
    private int reporterId;
    private Integer verifierId;
    private String content;
    private int category;
    private Integer result;
    private Integer userId;
    private Integer groupId;
    private Integer notificationId;
    private Timestamp createdAt;
    private Timestamp handledAt;
    //transient field
    private String username;
    private String groupName;
    private String reporterName;
	private String notificationSender="lover~~~";

	public String getNotifacationSender(){
		return notificationSender;
	}
	
	public void setNotificationSender(String notificationSender){
		this.notificationSender=notificationSender;
	}
	
    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "report_id", nullable = false)
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "reporter_id", nullable = false)
    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    @Basic
    @Column(name = "verifier_id", nullable = true)
    public Integer getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(Integer verifierId) {
        this.verifierId = verifierId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 45)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "category", nullable = false)
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Basic
    @Column(name = "result", nullable = true)
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "notification_id", nullable = true)
    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "handled_at", nullable = true)
    public Timestamp getHandledAt() {
        return handledAt;
    }

    public void setHandledAt(Timestamp handledAt) {
        this.handledAt = handledAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complaint complaint = (Complaint) o;

        if (reportId != complaint.reportId) return false;
        if (reporterId != complaint.reporterId) return false;
        if (category != complaint.category) return false;
        if (verifierId != null ? !verifierId.equals(complaint.verifierId) : complaint.verifierId != null) return false;
        if (content != null ? !content.equals(complaint.content) : complaint.content != null) return false;
        if (result != null ? !result.equals(complaint.result) : complaint.result != null) return false;
        if (userId != null ? !userId.equals(complaint.userId) : complaint.userId != null) return false;
        if (groupId != null ? !groupId.equals(complaint.groupId) : complaint.groupId != null) return false;
        if (notificationId != null ? !notificationId.equals(complaint.notificationId) : complaint.notificationId != null)
            return false;
        if (createdAt != null ? !createdAt.equals(complaint.createdAt) : complaint.createdAt != null) return false;
        if (handledAt != null ? !handledAt.equals(complaint.handledAt) : complaint.handledAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = reportId;
        result1 = 31 * result1 + reporterId;
        result1 = 31 * result1 + (verifierId != null ? verifierId.hashCode() : 0);
        result1 = 31 * result1 + (content != null ? content.hashCode() : 0);
        result1 = 31 * result1 + category;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (userId != null ? userId.hashCode() : 0);
        result1 = 31 * result1 + (groupId != null ? groupId.hashCode() : 0);
        result1 = 31 * result1 + (notificationId != null ? notificationId.hashCode() : 0);
        result1 = 31 * result1 + (createdAt != null ? createdAt.hashCode() : 0);
        result1 = 31 * result1 + (handledAt != null ? handledAt.hashCode() : 0);
        return result1;
    }
}
