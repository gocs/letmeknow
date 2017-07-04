package action.admin;

import action.BaseAction;
import model.GroupQueryForm;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class QueryGroupAction extends BaseAction {
    private String groupName;
    private Integer groupId;
    private Integer start;
    private Integer count;
    private GroupService groupService;
    private List<GroupQueryForm> groups;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<GroupQueryForm> getGroups() {
        return groups;
    }

    @Action(value="/admin/allGroups",results={@Result(type="json")})
    public String QueryAllGroups(){
        groups=groupService.queryallGroups();
        return SUCCESS;
    }

    @Action(value="/admin/fetchGroupById",results={@Result(type="json")})
    public String QueryUserById(){
        if(groupId==null) return SUCCESS;
        groups=groupService.queryGroupById(groupId);
        return SUCCESS;
    }

    @Action(value="/admin/fetchGroupByName",results={@Result(type="json")})
    public String QueryUserByUsername(){
        if(groupName==null) return SUCCESS;
        groups=groupService.queryGroupByName(groupName,start,count);
        return SUCCESS;
    }
}
