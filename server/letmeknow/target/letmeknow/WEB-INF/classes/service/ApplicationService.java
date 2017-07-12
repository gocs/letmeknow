package service;

import model.Application;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public interface ApplicationService {
    public List<Application> queryAllApplications();

    public boolean setApplicationResult(int applicationId,int result);
    }
