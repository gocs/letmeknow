package action.admin;

import action.BaseAction;
import model.User;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import service.UserService;

import java.sql.Timestamp;

/**
 * Created by admin on 2017/6/30.
 */
public class QueryUserDetailAction extends BaseAction {
    private Integer userId;
    private String message = "0";
    private UserService userService;
    private User user;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Action(value = "userDetail", results = {@Result(type = "json")})
    //@Action(value = "userDetail", results = {@Result(location="/IMG.jsp")})
    public String QueryUserDetail() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        message="1";
        return SUCCESS;
    }

    //删头像，改权限，删用户，封用户
    @Action(value = "promotePrivilege", results = {@Result(type = "json")})
    public String promotePrivilege() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setIs_admin(1);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "reducePrivilege", results = {@Result(type = "json")})
    public String reducePrivilege() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setIs_admin(0);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "disableUser", results = {@Result(type = "json")})
    public String disableUser() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setStatus(1);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "activateUser", results = {@Result(type = "json")})
    public String activateUser() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setStatus(2);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "deleteUser", results = {@Result(type = "json")})
    public String deleteUser() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        user.setStatus(0);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "restoreUser", results = {@Result(type = "json")})
    public String restoreUser() {
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user = userService.queryUserDetail(userId);
        user.setDeleted_at(null);
        user.setStatus(2);
        userService.updateUser(user);
        message = "1";
        return SUCCESS;
    }

    @Action(value = "deleteAvatar", results = {@Result(type = "json")})
    public String deleteAvatar(){
        if (!userService.adminExistUser(userId)) return SUCCESS;
        user=userService.queryUserDetail(userId);
        user.setAvatar(null);
        userService.updateUser(user);
        message="1";
        return SUCCESS;
    }

}
