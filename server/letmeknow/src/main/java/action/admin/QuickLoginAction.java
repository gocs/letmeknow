package action.admin;

import action.BaseAction;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;

/**
 * Created by admin on 2017/7/4.
 */
public class QuickLoginAction extends BaseAction {
    @Action(value="QuickLogin",results={@Result(location="/index.jsp")})
    public String QuickLogin(){
        session().setAttribute("username","admin");
        session().setAttribute("userid",1);
        session().setAttribute("isadmin","true");
        return SUCCESS;
    }
}
