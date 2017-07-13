package action;

import model.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/13.
 */
public class GroupDetailAction extends BaseAction {
    private GroupService groupService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();
    private Integer groupId;

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
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


    @Action(value="/common/groupDetail",results={@Result(type="json")})
    public String GroupDetail(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        GroupDetail groupDetail=groupService.queryGroupDetail(groupId);
        data.put("groupDetail",groupDetail);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/groupMember",results={@Result(type="json")})
    public String GroupMem(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        List<GroupMemWithAvatar> groupMems=groupService.queryCommonGroupMembers(groupId);
        data.put("groupMember",groupMems);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/groupNotifier",results={@Result(type="json")})
    public String GroupNotifier(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        List<GroupMemWithAvatar> groupN=groupService.queryGroupNotifiers(groupId);
        data.put("groupNotifier",groupN);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/groupMemberDetailList",results={@Result(type="json")})
    public String GroupMemberDetail(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        List<User> groupMemberDetail=groupService.queryCommonUserListByGroupId(groupId);
        data.put("groupMemberDetail",groupMemberDetail);
        code=1;
        message="success";
        return SUCCESS;
    }
}
