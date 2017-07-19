package model;

/**
 * Created by admin on 2017/7/13.
 */
public class GroupMemWithAvatar {
    private Integer userId;
    private String username;
    private Integer role;
    private String avatar;
    private String installationId;

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public GroupMemWithAvatar(GroupMem member, User user){
        int role;
        if(member.getRole().equals("master")) role=2;
        else if(member.getRole().equals("notifier")) role=1;
        else role=0;
        this.setRole(role);
        this.setUserId(member.getUserId());
        this.setUsername(user.getUsername());
        this.setAvatar(user.getAvatar());
        this.setInstallationId(user.getInstallationId());
    }
}
