package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by admin on 2017/7/6.
 */
public class NotLoginInterceptor extends AbstractInterceptor{
    public String intercept(ActionInvocation invocation) throws Exception {
        //TODO: you cannot login when you've already logged in
        String actionName=invocation.getProxy().getActionName();
        if(!(actionName.equals("login"))) return invocation.invoke();
        if(ServletActionContext.getRequest().getSession().getAttribute("username")==null) return invocation.invoke();
        return Action.INPUT;
    }
}
