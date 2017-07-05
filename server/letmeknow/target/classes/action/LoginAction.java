package action;

import model.Reply;
import model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.LoginService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 2017/6/27.
 */
public class LoginAction extends BaseAction {
    private LoginService loginService;
    private String username;
    private String password;
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

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }


    @Action(value = "login", results = {@Result(name = SUCCESS, type = "json")})
    public String login() throws IOException {
        if (username == null || password == null || username.equals("") || password.equals("")) {
            code = 0;
            message="username and password cannot be null";
            return SUCCESS;
        }
        User user = new User().construct_user(username, password, null, null);
        Reply reply = loginService.login(user);
        code = reply.getCode();
        message = reply.getMessage();
        data = reply.getData();
        return SUCCESS;
    }

    @Action(value = "checkLogin", results = {@Result(name = SUCCESS, type = "json")})
    public String checkLogin() {
        if (session().getAttribute("username") != null) {
            code = 1;
            data.put("username", session().getAttribute("username"));
        } else code = 0;
        return SUCCESS;
    }
}