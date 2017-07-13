package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.GroupService;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/13.
 */
public class UpdateUserAction extends BaseAction{
    private UserService userService;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();
    private Integer userId;
    private String username;
    private String avatar;
    private String email;
    private Integer phoneNum;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
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

    @Action(value="/common/updateUsername",results={@Result(type="json")})
    public String updateUsername(){
        if(!userService.adminExistUser(userId)) {
            message="invalid userId";
            return SUCCESS;
        }
        userService.updateUser(userId, username,null,null,null);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/updateUserAvatar",results={@Result(type="json")})
    public String updateAvatar(){
        if(!userService.adminExistUser(userId)) {
            message="invalid userId";
            return SUCCESS;
        }
        userService.updateUser(userId, null,avatar,null,null);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/updateUserEmail",results={@Result(type="json")})
    public String updateEmail(){
        if(!userService.adminExistUser(userId)) {
            message="invalid userId";
            return SUCCESS;
        }
        userService.updateUser(userId, null,null,email,null);
        code=1;
        message="success";
        return SUCCESS;
    }

    @Action(value="/common/updateUserPhoneNum",results={@Result(type="json")})
    public String updatePhoneNum(){
        if(!userService.adminExistUser(userId)) {
            message="invalid userId";
            return SUCCESS;
        }
        userService.updateUser(userId, null,null,null,phoneNum);
        code=1;
        message="success";
        return SUCCESS;
    }
}
