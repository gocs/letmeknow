package dao.impl;

import dao.ComplaintDao;
import model.Complaint;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ComplaintDaoImpl extends CommonDaoImpl<Complaint> implements ComplaintDao {
    public List<Complaint> getAllComplaints() {
        return (List<Complaint>) getHibernateTemplate().find("from Complaint as c where c.result=0");
    }

    public Complaint getComplaintById(int complaintId){
        List<Complaint> res=(List<Complaint>)getHibernateTemplate().find("from Complaint as c where c.reportId=? and c.result=0",complaintId);
        return res.size()==0?null:res.get(0);
    }
}
