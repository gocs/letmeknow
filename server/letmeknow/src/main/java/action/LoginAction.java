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
    private static final long serialVersionUID = 1L;
    private String message;
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Action(value="login",results={@Result(name=SUCCESS,type="json")})
    public String login() throws IOException {
        //测试用逻辑……
        String[] temp=request().getParameterMap().get((Object)"json_data");
        String res="";
        for(String s:temp)
            res+=s;
        Gson gson=new Gson();
        LoginForm form=gson.fromJson(res,LoginForm.class);
        //end 测试用逻辑
        if(loginService.login(form))
        {
            session().setAttribute("islogin","true");
            message="success!";
        }
        else message="invalid!";
        return SUCCESS;
    }
}