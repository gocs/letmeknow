package service.impl;

import dao.UserDao;
import model.User;
import model.UserQueryForm;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserQueryForm> queryUserById(int userId) {
        List<UserQueryForm> res=new ArrayList<UserQueryForm>();
        res.add(new UserQueryForm(userDao.getUserById(userId)));
        return res;
    }

    public List<UserQueryForm> queryUserByName(String username, int start, int count) {
        return UserQueryForm.convertToForm(userDao.getUserByName(username, start, count));
    }

    public List<UserQueryForm> queryAllUsers() {
        return UserQueryForm.convertToForm(userDao.getAllUsers());
    }

    public User queryUserDetail(int userId) {
        return userDao.getUserById(userId);
    }

    /* deprecated
    public String PromotePrivilege(int userId) {
        User user=userDao.getUserById(userId);
        if(user==null) return "no such user";
        if(user.getIs_admin().equals("admin")) return "already an admin";
        user.setIs_admin("admin");
        userDao.update(user);
        return "update complete";
    }
    */

    public void updateUser(User user){
        userDao.update(user);
    }

    public boolean adminExistUser(int userId) {
        if(userDao.getUserById(userId)!=null) return true;
        return false;
    }
}
