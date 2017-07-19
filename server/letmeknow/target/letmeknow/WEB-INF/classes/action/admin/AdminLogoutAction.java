package action.admin;

import action.BaseAction;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;


/**
 * Created by admin on 2017/7/12.
 */
public class AdminLogoutAction extends BaseAction {
    private String message="success";

    public String getMessage() {
        return message;
    }

    @Action(value="/admin/logout",results={@Result(type="json")})
    public String logout(){
        session().invalidate();
        return SUCCESS;
    }
}
