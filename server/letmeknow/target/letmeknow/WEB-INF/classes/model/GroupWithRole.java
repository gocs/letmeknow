package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/13.
 */
public class GroupWithRole {
    private int groupId;
    private String groupName;
    private String role;

    public GroupWithRole(int groupId, String groupName, String role) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.role = role;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static List<GroupWithRole> convertToForm(List<Object[]> groups){
        List<GroupWithRole> res =new ArrayList<GroupWithRole>();
        for(Object[] composition:groups){
            res.add(new GroupWithRole(((Groups)composition[0]).getGroupId(),((Groups)composition[0]).getGroupName(),((GroupMem)composition[1]).getRole()));
        }
        return res;
    }
}
