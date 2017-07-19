package action.admin;

import action.BaseAction;
import model.GroupMem;
import model.GroupMemQueryForm;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.List;

/**
 * Created by admin on 2017/7/6.
 */
public class QueryGroupMemberAction extends BaseAction {
    private Integer groupId;
    private List<GroupMemQueryForm> members;
    private GroupService groupService;
    private String message="0";

    public List<GroupMemQueryForm> getMembers() {
        return members;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Action(value = "/admin/groupMembers", results = {@Result(type = "json")})
    public String queryGroupMember(){
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        members=groupService.queryGroupMembers(groupId);
        message="1";
        return SUCCESS;
    }
}
