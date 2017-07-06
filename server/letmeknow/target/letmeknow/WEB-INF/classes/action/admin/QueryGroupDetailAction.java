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
    public String QueryUserDetail() {
        if (!groupService.adminExistGroup(groupId)) return SUCCESS;
        group=groupService.queryGroupDetail(groupId);
        message = "1";
        return SUCCESS;
    }
}
