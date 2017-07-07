package dao.impl;

import dao.ApplicationDao;
import model.Application;

import java.util.List;

/**
 * Created by admin on 2017/7/7.
 */
public class ApplicationDaoImpl extends CommonDaoImpl<Application> implements ApplicationDao {
    public List<Application> getAllApplications(){
        return (List<Application>)getHibernateTemplate().find("from Application as a where a.category=0 and a.result=0");
    }
}
