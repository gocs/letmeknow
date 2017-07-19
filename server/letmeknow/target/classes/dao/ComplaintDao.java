package dao;

import model.Complaint;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public interface ComplaintDao extends CommonDao<Complaint> {
    public List<Complaint> getAllComplaints();

    public Complaint getComplaintById(int complaintId);

}
