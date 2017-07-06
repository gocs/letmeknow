package model;

/**
 * Created by admin on 2017/7/6.
 */
public class GroupMemQueryForm {
    private Integer user_id;
    private String username;
    private Integer role;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
        this.setUser_id(member.getUserId());
        this.setUsername(user.getUsername());
    }
}
