package service.impl;

import dao.ApplicationDao;
import dao.GroupDao;
import dao.UserDao;
import model.Application;
import service.ApplicationService;
import model.Groups;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDao applicationDao;
    private UserDao userDao;
    private GroupDao groupDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public List<Application> queryAllApplications(){
        List<Application> res=applicationDao.getAllApplications();
        for (Application application:res){
            application.setApplierName(userDao.getUserById(application.getApplierId()).getUsername());
            application.setGroupName(groupDao.getGroupsById(application.getGroupId()).getGroupName());
        }
        return res;
    }

    public boolean setApplicationResult(int applicationId,int result){
        Application application=applicationDao.getApplicationById(applicationId);
        if(application==null) return false;
        //concurrency just won't matter
        application.setResult(result);
        application.setHandledAt(new Timestamp(System.currentTimeMillis()));
        applicationDao.update(application);
        if(result==1) {
            Groups group = groupDao.getGroupsById(application.getGroupId());
            group.setCategory(1);
            groupDao.update(group);
        }
        return true;
    }
}
