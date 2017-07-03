package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class AuthInterceptor extends AbstractInterceptor {
    /**
     *
     */
    private static final long serialVersionUID = -5114658085937727056L;
    private List<String> authActions = Arrays.asList("allGroups", "allUsers", "FetchGroupByName", "/FetchUserByName","/FetchGroupById","/FetchUserById");
    private String message = "no privilege!";

    public String getMessage() {
        return message;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getProxy().getActionName();
        if (!(authActions.contains(actionName)))
            return invocation.invoke();
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("isadmin") != null && session.getAttribute("isadmin").equals("true"))
            return invocation.invoke();
        return "auth";
    }

}
