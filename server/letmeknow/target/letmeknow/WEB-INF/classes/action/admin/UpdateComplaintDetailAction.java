package action.admin;

import action.BaseAction;
import model.Complaint;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.ComplaintService;

/**
 * Created by admin on 2017/7/7.
 *
 */

//TODO: does the complaint really exist
public class UpdateComplaintDetailAction extends BaseAction {
    private ComplaintService complaintService;
    private Complaint complaint;
    private String message="0";
    private Integer reportId;

    public void setComplaintService(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public String getMessage() {
        return message;
    }

    @Action(value = "/admin/closeComplaint", results = {@Result(type = "json")})
    public String closeComplaint(){
        if((complaint=complaintService.closeComplaint(reportId))==null)
            return SUCCESS;
        message="1";
        return SUCCESS;
    }
}
