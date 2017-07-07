package service.impl;

import dao.ApplicationDao;
import model.Application;
import service.ApplicationService;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDao applicationDao;

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public List<Application> queryAllApplications(){
        return applicationDao.getAllApplications();
    }
}
