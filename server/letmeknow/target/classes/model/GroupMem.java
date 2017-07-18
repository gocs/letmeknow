package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 2017/7/5.
 */
@Entity
@Table(name = "group_mem", schema = "letmeknow_test", catalog = "")
@IdClass(GroupMemPK.class)
public class GroupMem {
    private int groupId;
    private int userId;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    @Id
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "updated_at", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = false)
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMem groupMem = (GroupMem) o;

        if (groupId != groupMem.groupId) return false;
        if (userId != groupMem.userId) return false;
        if (role != null ? !role.equals(groupMem.role) : groupMem.role != null) return false;
        if (createdAt != null ? !createdAt.equals(groupMem.createdAt) : groupMem.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(groupMem.updatedAt) : groupMem.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(groupMem.deletedAt) : groupMem.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + userId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }

    public GroupMem(int userId,int groupId,String role){
        this.userId=userId;
        this.groupId=groupId;
        this.role=role;
    }

    public GroupMem(){}
}
