package action;

import model.User;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.LoginService;


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

    public String getMessage() {
        return message;
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
        if (loginService.register(username, password, email, phone_num) > 0) message = "register succeeded";
        else message = "register failed";
        return SUCCESS;
    }
}
