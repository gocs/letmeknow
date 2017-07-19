package service;

import model.Complaint;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public interface ComplaintService {
    public List<Complaint> queryAllComplaints();

    public Complaint closeComplaint(int complaintId);

}
