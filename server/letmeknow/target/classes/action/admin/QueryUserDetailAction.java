package action.admin;

import action.BaseAction;
import model.User;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import service.UserService;

/**
 * Created by admin on 2017/6/30.
 */
@ParentPackage("json-default")
public class QueryUserDetailAction extends BaseAction {
    private Integer userId;
    private UserService userService;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Action(value="userDetail",results={@Result(type="json")})
    public String QueryUserDetail() {
        user = userService.queryUserDetail(userId);
        return SUCCESS;
    }
}
