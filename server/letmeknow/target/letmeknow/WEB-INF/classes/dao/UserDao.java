package dao;

import model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by admin on 2017/6/27.
 */
public interface UserDao {
    public Integer save(User user);

    public void delete(User user);

    public void update(User user);

    public User getUserById(int id);

    public List<User> getAllUsers();

    public User getUserByName(String username);

    public List<User> getUserByName(String username, int start, int count);

    public User getCommonUserById(int id);

    public List<User> getCommonUserListByGroupId(int groupId);

    }
