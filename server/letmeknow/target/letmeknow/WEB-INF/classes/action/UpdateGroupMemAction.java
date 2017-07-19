package action;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.GroupMem;
import model.Reply;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/14.
 */
public class UpdateGroupMemAction extends BaseAction{
    private GroupService groupService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();
    private Integer groupId;
    private String userId;

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Action(value="/common/kickOutFromGroup",results={@Result(type="json")})
    public String kickOutFromGroup(){
        if(!groupService.adminExistGroup(groupId)) {
            message="invalid groupId";
            return SUCCESS;
        }
        if(!groupService.groupMasterTest(groupId,(Integer)session().getAttribute("userid"))){
            message="not enough privilege";
            return SUCCESS;
        }
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        List<Integer> userList=gson.fromJson(userId,type);
        Reply reply=groupService.removeGroupMembers(userList,groupId);
        code=reply.getCode();
        message=reply.getMessage();
        data=reply.getData();
        return SUCCESS;
    }

    @Action(value="/quitGroup",results={@Result(type="json")})
    public String quitGroup(){
        if(!groupService.adminExistGroup(groupId)){
            message="invalid group";
            return SUCCESS;
        }
        groupService.removeGroupMember((Integer)session().getAttribute("userid"),groupId);
        code=1;
        message="success";
        return SUCCESS;
    }


    @Action(value="/joinGroup",results={@Result(type="json")})
    public String joinGroup(){
        if(!groupService.adminExistGroup(groupId)){
            message="invalid group";
            return SUCCESS;
        }
        GroupMem groupMem=new GroupMem((Integer)(session().getAttribute("userid")),groupId,"regular");
        Reply reply=groupService.addGroupMember(groupMem);
        code=reply.getCode();
        message=reply.getMessage();
        data=reply.getData();
        return SUCCESS;
    }

    @Action(value="/common/addToGroup",results={@Result(type="json")})
    public String addToGroup(){
        if(!groupService.adminExistGroup(groupId)){
            message="invalid group";
            return SUCCESS;
        }
        if(!groupService.groupMasterTest(groupId,(Integer)session().getAttribute("userid"))){
            message="not enough privilege";
            return SUCCESS;
        }
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        List<Integer> userList=gson.fromJson(userId,type);
        Reply reply=groupService.addGroupMembers(userList,groupId);
        code=reply.getCode();
        data=reply.getData();
        message=reply.getMessage();
        return SUCCESS;
    }

    @Action(value="/common/changeToNotifier",results={@Result(type="json")})
    public String changeToNotifier(){
        if(!groupService.adminExistGroup(groupId)){
            message="invalid group";
            return SUCCESS;
        }
        if(!groupService.groupMasterTest(groupId,(Integer)session().getAttribute("userid"))){
            message="not enough privilege";
            return SUCCESS;
        }
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        List<Integer> userList=gson.fromJson(userId,type);
        Reply reply=groupService.changeGroupMemberPrivilege(groupId,userList,1);
        code=reply.getCode();
        message=reply.getMessage();
        data=reply.getData();
        return SUCCESS;
    }
}
