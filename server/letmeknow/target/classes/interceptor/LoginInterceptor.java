package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017/7/13.
 */
public class LoginInterceptor extends AbstractInterceptor{
    private String message = "no privilege!";

    public String getMessage() {
        return message;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String nameSpace = invocation.getProxy().getNamespace();
        if(nameSpace.equals("/common")) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            if (session.getAttribute("username")!=null)
                return invocation.invoke();
            return Action.INPUT;
        }
        return invocation.invoke();
    }

}
