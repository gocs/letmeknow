package service.impl;

import dao.ApplicationDao;
import dao.GroupDao;
import dao.UserDao;
import model.Application;
import service.ApplicationService;

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
}
