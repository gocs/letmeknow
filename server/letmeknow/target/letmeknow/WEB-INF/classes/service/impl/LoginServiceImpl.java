package service.impl;

import dao.UserDao;
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

    public int register(String username, String password, String email, Integer phone_num) {
        User user=new User().construct_user(username,password,email,phone_num);
        User target=userDao.getUserByName(username);
        if(target==null) {
            int id=userDao.save(user);
            ServletActionContext.getRequest().getSession().setAttribute("userid",user.getUser_id());
            ServletActionContext.getRequest().getSession().setAttribute("username",user.getUsername());
            return id;
        }
        return -1;
    }

    public String adminLogin(String username, String password) {
        User user=userDao.getUserByName(username);
        if(user==null||!(password.equals(user.getPassword()))) return "用户名或密码错误";
        if(!(user.getIs_admin().equals("admin"))) return "权限不足";
        HttpSession session=ServletActionContext.getRequest().getSession();
        session.setAttribute("userid",user.getUser_id());
        session.setAttribute("username",user.getUsername());
        session.setAttribute("isadmin","true");
        return "登陆成功";
    }
}
