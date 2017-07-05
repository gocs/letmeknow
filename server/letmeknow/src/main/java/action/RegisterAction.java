package action;

import model.Reply;
import model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.LoginService;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 2017/6/29.
 */

public class RegisterAction extends BaseAction {
    private String username;
    private String password;
    private String email;
    private Integer phone_num;
    private LoginService loginService;
    private String message;
    private Integer code;
    private Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_num(Integer phone_num) {
        this.phone_num = phone_num;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Action(value = "register", results = {@Result(type = "json", name = SUCCESS)})
    public String register() {
        if (username == null || password == null || username.equals("") || password.equals("")) {
            code=0;
            message="username and password cannot be null";
            return SUCCESS;
        }
        Reply reply= loginService.register(new User().construct_user(username,password,email,phone_num));
        code=reply.getCode();
        message=reply.getMessage();
        data=reply.getData();
        return SUCCESS;
    }
}
