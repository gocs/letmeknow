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
public class GroupMemberAction extends BaseAction{
    private GroupService groupService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();
    private int userId;

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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Action(value="/common/groupMemberDetail",results={@Result(type="json")})
    public String GroupMemberDetail(){
        User groupMemberDetail=groupService.queryCommonUserByUserId(userId);
        data.put("groupMemberDetail",groupMemberDetail);
        code=1;
        message="success";
        return SUCCESS;
    }
}
