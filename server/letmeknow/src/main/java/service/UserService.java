package service;

import model.User;
import model.UserQueryForm;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public interface UserService {
    public List<UserQueryForm> queryUserById(int userId);

    public List<UserQueryForm> queryUserByName(String username, int start, int count);

    public List<UserQueryForm> queryAllUsers();

    public User queryUserDetail(int userId);

    //public String PromotePrivilege(int userId);
    public void updateUser(User user);

    public boolean adminExistUser(int userId);

    public User setUserStatus(int userId, int status);

    public User deleteAvatar(int userId);

    public User SetUserPrivilege(int userId, int privilege);
    }
