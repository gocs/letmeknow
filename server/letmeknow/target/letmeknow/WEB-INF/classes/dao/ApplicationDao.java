package dao;

import model.Application;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public interface ApplicationDao extends CommonDao<Application> {
    public List<Application> getAllApplications();

    public Application getApplicationById(int applicationId);
    }
