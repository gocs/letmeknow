package service.impl;

import dao.UserDao;
import model.LoginForm;
import model.User;
import service.LoginService;

/**
 * Created by admin on 2017/6/28.
 */
public class LoginServiceImpl implements LoginService{
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(LoginForm loginForm){
        String username=loginForm.getUsername();
        String password=loginForm.getPassword();

        User user=userDao.getUserByName(username);
        if(user==null) return false;
        if(!(password.equals(user.getPassword()))) return false;
        return true;
        /*
        if(username.equals("aaa")&&password.equals("bbb"))
            return true;
        return false;
        */
    }
}
