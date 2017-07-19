package action.admin;

import action.BaseAction;
import model.GroupDetail;
import model.Groups;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.Map;

/**
 * Created by admin on 2017/6/30.
 */
public class QueryGroupDetailAction extends BaseAction {
    private Integer groupId;
    private String message = "0";
    private GroupService groupService;
    private GroupDetail group;

    public GroupDetail getGroup() {
        return group;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }


    public String getMessage() {
        return message;
    }


    @Action(value = "/admin/groupDetail", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String QueryGroupDetail() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.queryGroupDetail(groupId);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/setGroupToPublic", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String setGroupToPublic() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupPrivilege(groupId,1);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/setGroupToPrivate", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String setGroupToPrivate() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupPrivilege(groupId,0);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/disableGroup", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String disableGroup() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupStatus(groupId,1);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/activateGroup", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String activateGroup() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupStatus(groupId,2);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/deleteGroup", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String deleteGroup() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupStatus(groupId,0);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/restoreGroup", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String restoreGroup() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.setGroupStatus(groupId,2);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "/admin/deleteGroupIcon", results = {@Result(type = "json")})
    public String deleteGroupIcon(){
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.deleteGroupIcon(groupId);
        message="1";
        return SUCCESS;
    }
}
