package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
        List<User> users = (List<User>) getHibernateTemplate().find("from User as u where u.user_id=?", id);
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getAllUsers() {
        return (List<User>) getHibernateTemplate().find("from User");
    }

    public User getUserByName(String username) {
        List<User> users = (List<User>) getHibernateTemplate().find("from User as u where u.username=?", username);
        return users.size() > 0 ? users.get(0) : null;
    }
}
