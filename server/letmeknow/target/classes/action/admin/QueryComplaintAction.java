package action.admin;

import action.BaseAction;
import model.Complaint;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.ComplaintService;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class QueryComplaintAction extends BaseAction {
    private List<Complaint> complaints;
    private ComplaintService complaintService;

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaintService(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Action(value = "/admin/allComplaints", results = {@Result(type = "json")})
    public String allComplaints(){
        complaints=complaintService.queryAllComplaints();
        return SUCCESS;
    }


}
