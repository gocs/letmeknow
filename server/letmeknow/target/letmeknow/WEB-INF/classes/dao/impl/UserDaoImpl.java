package dao.impl;

import dao.UserDao;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2017/6/28.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    public Integer save(User user) {
        return (Integer) getHibernateTemplate().save(user);
    }

    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }

    public void update(User user) {
        getHibernateTemplate().update(user);
    }

    public User getUserById(int id) {
        List<User> users = (List<User>) getHibernateTemplate().find("from User as u where u.userId=?", id);
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getAllUsers() {
        return (List<User>) getHibernateTemplate().find("from User");
    }

    public User getUserByName(String username) {
        List<User> users = (List<User>) getHibernateTemplate().find("from User as u where u.username=?", username);
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getUserByName(final String username, final int start, final int count) {
        return (List<User>) getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery("from User where username like '%"+username+"%'").setFirstResult(start).setMaxResults(count).list();
            }
        });
    }
}
