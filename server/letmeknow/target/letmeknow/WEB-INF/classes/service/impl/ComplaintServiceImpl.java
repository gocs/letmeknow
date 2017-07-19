package service.impl;

import dao.ComplaintDao;
import dao.GroupDao;
import dao.UserDao;
import model.Complaint;
import service.ComplaintService;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ComplaintServiceImpl implements ComplaintService{
    private ComplaintDao complaintDao;
    private UserDao userDao;
    private GroupDao groupDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public ComplaintDao getComplaintDao() {
        return complaintDao;
    }

    public void setComplaintDao(ComplaintDao complaintDao) {
        this.complaintDao = complaintDao;
    }

    public List<Complaint> queryAllComplaints() {
        List<Complaint> res=complaintDao.getAllComplaints();
        for(Complaint complaint:res){
            complaint.setReporterName(userDao.getUserById(complaint.getReporterId()).getUsername());
            if(complaint.getUserId()!=null)complaint.setUsername(userDao.getUserById(complaint.getUserId()).getUsername());
            if(complaint.getGroupId()!=null) complaint.setGroupName(groupDao.getGroupsById(complaint.getGroupId()).getGroupName());
        }
        return res;
    }

    public Complaint closeComplaint(int complaintId){
        Complaint complaint=complaintDao.getComplaintById(complaintId);
        if(complaint==null) return null;
        complaint.setResult(1);
        complaint.setReporterName(userDao.getUserById(complaint.getReporterId()).getUsername());
        if(complaint.getUserId()!=null)complaint.setUsername(userDao.getUserById(complaint.getUserId()).getUsername());
        if(complaint.getGroupId()!=null) complaint.setGroupName(groupDao.getGroupsById(complaint.getGroupId()).getGroupName());
        complaintDao.update(complaint);
        return complaint;
    }
}
