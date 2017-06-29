package action;

import com.google.gson.Gson;
import model.LoginForm;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.annotations.Parameter;
import service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by admin on 2017/6/27.
 */
@ParentPackage("json-default")
public class LoginAction extends BaseAction {
    private String message;
    private LoginService loginService;
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String getMessage() {
        return message;
    }

    @Action(value = "login", results = {@Result(name = SUCCESS, type = "json")})
    public String login() throws IOException {
        HttpServletRequest request = request();
        request.setCharacterEncoding("utf-8");
        /*测试用逻辑……
        String[] temp=request().getParameterMap().get((Object)"json_data");
        String res="";
        for(String s:temp)
            res+=s;
        Gson gson=new Gson();
        LoginForm form=gson.fromJson(res,LoginForm.class);
        end 测试用逻辑*/
        if (loginService.login(username, password)) message = "success!";
        else message = "invalid!";
        return SUCCESS;
    }
}