package action;

import model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/13.
 */
public class ModifyGroupAction extends BaseAction{
    private GroupService groupService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();
    private Integer groupId;
    private String introduction;
    private List<Integer> users;
    private String groupName;
    private String avatar;

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Action(value="/common/updateGroupIntroduction",results={@Result(type="json")})
    public String updateIntroduction(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        groupService.updateGroup(groupId, introduction,null,null);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/updateGroupAvatar",results={@Result(type="json")})
    public String updateAvatar(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        groupService.updateGroup(groupId, null,avatar,null);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/updateGroupName",results={@Result(type="json")})
    public String updateName(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        groupService.updateGroup(groupId, null,null,groupName);
        code=1;
        message="success";
        return SUCCESS;
    }
}
