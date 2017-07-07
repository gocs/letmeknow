package service.impl;

import dao.ComplaintDao;
import model.Complaint;
import service.ComplaintService;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ComplaintServiceImpl implements ComplaintService{
    private ComplaintDao complaintDao;

    public ComplaintDao getComplaintDao() {
        return complaintDao;
    }

    public void setComplaintDao(ComplaintDao complaintDao) {
        this.complaintDao = complaintDao;
    }

    public List<Complaint> queryAllComplaints() {
        return complaintDao.getAllComplaints();
    }

    public Complaint closeComplaint(int complaintId){
        Complaint complaint=complaintDao.getComplaintById(complaintId);
        if(complaint==null) return null;
        complaint.setResult(1);
        complaintDao.update(complaint);
        return complaint;
    }
}
