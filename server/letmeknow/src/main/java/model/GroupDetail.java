package model;

/**
 * Created by admin on 2017/7/6.
 */
public class GroupDetail extends Groups{
    private String master;
    private Integer members;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public GroupDetail(Groups group,String master,Integer members){
        this.setCapacity(group.getCapacity());
        this.setCategory(group.getCategory());
        this.setCreatedAt(group.getCreatedAt());
        this.setDeletedAt(group.getDeletedAt());
        this.setUpdatedAt(group.getUpdatedAt());
        this.setGroupId(group.getGroupId());
        this.setIcon(group.getIcon());
        this.setGroupName(group.getGroupName());
        this.setStatus(group.getStatus());
        this.setMembers(members);
        this.setMaster(master);
        this.setIntroduction(group.getIntroduction());
    }
}
