package action.admin;

import action.BaseAction;
import model.Application;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.ApplicationService;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class QueryApplicationAction extends BaseAction {
    private ApplicationService applicationService;
    private List<Application> applications;

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public List<Application> getApplications() {
        return applications;
    }

    @Action(value="/admin/allApplications",results={@Result(type="json",name=SUCCESS)})
    public String allApplication(){
        applications=applicationService.queryAllApplications();
        return SUCCESS;
    }
}
