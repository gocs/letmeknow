package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupQueryForm {
    private String groupName;
    private Integer groupId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupQueryForm(Groups group){
        this.groupName=group.getGroupName();
        this.groupId=group.getGroupId();
    }

    public static List<GroupQueryForm> convertToForm(List<Groups> groups){
        List<GroupQueryForm> forms=new ArrayList<GroupQueryForm>();
        for(Groups group:groups) forms.add(new GroupQueryForm(group));
        return forms;
    }
}
