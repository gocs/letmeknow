package model;

/**
 * Created by admin on 2017/7/6.
 */
public class GroupMemQueryForm {
    private Integer userId;
    private String username;
    private Integer role;

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

    public GroupMemQueryForm(GroupMem member, User user){
        int role;
        if(member.getRole().equals("master")) role=2;
        else if(member.getRole().equals("administrator")) role=1;
        else role=0;
        this.setRole(role);
        this.setUserId(member.getUserId());
        this.setUsername(user.getUsername());
    }
}
