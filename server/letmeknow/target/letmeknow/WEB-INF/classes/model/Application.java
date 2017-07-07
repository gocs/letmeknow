package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by admin on 2017/7/7.
 */
@Entity
public class Application {
    private int applicationId;
    private int applierId;
    private Integer verifierId;
    private String content;
    private int groupId;
    private Integer result;
    private int category;

    @Id
    @Column(name = "application_id", nullable = false)
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "applier_id", nullable = false)
    public int getApplierId() {
        return applierId;
    }

    public void setApplierId(int applierId) {
        this.applierId = applierId;
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
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
    @Column(name = "category", nullable = false)
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (applicationId != that.applicationId) return false;
        if (applierId != that.applierId) return false;
        if (groupId != that.groupId) return false;
        if (category != that.category) return false;
        if (verifierId != null ? !verifierId.equals(that.verifierId) : that.verifierId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = applicationId;
        result1 = 31 * result1 + applierId;
        result1 = 31 * result1 + (verifierId != null ? verifierId.hashCode() : 0);
        result1 = 31 * result1 + (content != null ? content.hashCode() : 0);
        result1 = 31 * result1 + groupId;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + category;
        return result1;
    }
}
