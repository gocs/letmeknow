package dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dao.CommonDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements CommonDao<T> {
    public void save(T object) {
        getHibernateTemplate().save(object);
    }

    public void delete(T object) {
        getHibernateTemplate().delete(object);
    }

    public void update(T object) {
        getHibernateTemplate().update(object);
    }
}
