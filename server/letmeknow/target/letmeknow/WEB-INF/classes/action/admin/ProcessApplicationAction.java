package action.admin;

import action.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.ApplicationService;

/**
 * Created by admin on 2017/7/10.
 */
public class ProcessApplicationAction extends BaseAction {
    private ApplicationService applicationService;
    private String message="0";
    private int applicationId;

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getMessage() {
        return message;
    }

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Action(value="/admin/passApplication",results={@Result(type="json",name=SUCCESS)})
    public String passApplication(){
        if(applicationService.setApplicationResult(applicationId,1))
            message="1";
        return SUCCESS;
    }

    @Action(value="/admin/denyApplication",results={@Result(type="json",name=SUCCESS)})
    public String denyApplication(){
        if(applicationService.setApplicationResult(applicationId,0))
            message="1";
        return SUCCESS;
    }
}
