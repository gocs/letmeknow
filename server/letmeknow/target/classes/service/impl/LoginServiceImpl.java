package service.impl;

import dao.UserDao;
import model.LoginForm;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.LoginService;

import javax.servlet.http.HttpSession;

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

    public boolean login(String username, String password){
        User user=userDao.getUserByName(username);
        if(user==null) return false;
        if(!(password.equals(user.getPassword()))) return false;
        HttpSession session=ServletActionContext.getRequest().getSession();
        session.setAttribute("userid",user.getUser_id());
        session.setAttribute("username",user.getUsername());
        return true;
    }

    public boolean register(String username, String password, String email, Integer phone_num) {
        User user=new User().construct_user(username,password,email,phone_num);
        if(userDao.save(user)>0) {
            ServletActionContext.getRequest().getSession().setAttribute("userid",user.getUser_id());
            ServletActionContext.getRequest().getSession().setAttribute("username",user.getUsername());
            return true;
        }
        return false;
    }

    public boolean adminLogin(String username, String password) {
        User user=userDao.getUserByName(username);
        if(user==null) return false;
        if(!(password.equals(user.getPassword()))) return false;
        if(!(user.getIs_admin().equals("admin"))) return false;
        HttpSession session=ServletActionContext.getRequest().getSession();
        session.setAttribute("userid",user.getUser_id());
        session.setAttribute("username",user.getUsername());
        session.setAttribute("isadmin","true");
        return true;
    }
}
