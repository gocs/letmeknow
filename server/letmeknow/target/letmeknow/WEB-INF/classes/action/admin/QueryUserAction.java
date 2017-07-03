package action.admin;

import action.BaseAction;
import model.User;
import model.UserQueryForm;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import service.UserService;

import org.apache.struts2.convention.annotation.Action;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public class QueryUserAction extends BaseAction {
    private String username;
    private Integer userId;
    private Integer start;
    private Integer count;
    private UserService userService;
    private List<UserQueryForm> users;

    public List<UserQueryForm> getUsers() {
        return users;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Action(value="allUsers",results={@Result(type="json")})
    public String QueryAllUsers(){
        users=userService.queryAllUsers();
        return SUCCESS;
    }

    @Action(value="FetchUserById",results={@Result(type="json")})
    public String QueryUserById(){
        if(userId==null) return SUCCESS;
        users=userService.queryUserById(userId);
        return SUCCESS;
    }

    @Action(value="FetchUserByName",results={@Result(type="json")})
    public String QueryUserByUsername(){
        if(username==null) return SUCCESS;
        users=userService.queryUserByName(username,start,count);
        return SUCCESS;
    }
}
