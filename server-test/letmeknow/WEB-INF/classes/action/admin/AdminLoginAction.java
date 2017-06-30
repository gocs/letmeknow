package action.admin;

import action.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import service.LoginService;

/**
 * Created by admin on 2017/6/29.
 */
@ParentPackage("json-default")
public class AdminLoginAction extends BaseAction {
    private String username;
    private String password;
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

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Action(value="adminlogin",results={@Result(type="json",name=SUCCESS)})
    public String adminLogin(){
        message=loginService.adminLogin(username,password);
        return SUCCESS;
    }
}
