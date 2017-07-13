package action;

import action.BaseAction;
import model.GroupQueryForm;
import model.GroupWithRole;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/13.
 */
public class GroupAction extends BaseAction {
    private GroupService groupService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }


    @Action(value="/common/allGroups",results={@Result(type="json")})
    public String QueryAllGroups(){
        List<GroupWithRole> groups=groupService.CommonGetGroups((Integer)session().getAttribute("userid"));
        data.put("groups",groups);
        code=1;
        message="success";
        return SUCCESS;
    }
}
