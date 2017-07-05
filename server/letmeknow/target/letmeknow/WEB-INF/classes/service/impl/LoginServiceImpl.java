package service.impl;

import dao.UserDao;
import model.Reply;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.LoginService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    public Reply login(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        User target=userDao.getUserByName(username);
        if(target==null) return new Reply(0,"username/password error",null);
        if(!(password.equals(target.getPassword()))) return new Reply(0,"username/password error",null);
        HttpSession session=ServletActionContext.getRequest().getSession();
        session.setAttribute("userid",target.getUserId());
        session.setAttribute("username",target.getUsername());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("user",(Object)target);
        return new Reply(1,"login succeed",map);
    }

    public Reply register(User user) {
        if(user.getPassword().length()<=8) return new Reply(0,"password too short:8 characters needed at least",null);
        User target=userDao.getUserByName(user.getUsername());
        if(target==null) {//no such user:register success
            int id=userDao.save(user);
            ServletActionContext.getRequest().getSession().setAttribute("userid",user.getUserId());
            ServletActionContext.getRequest().getSession().setAttribute("username",user.getUsername());
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("user",(Object)target);
            return new Reply(1,"register succeeded",map);
        }
        return new Reply(0,"username exists in database",null);
    }

    public String adminLogin(String username, String password) {
        User user=userDao.getUserByName(username);
        if(user==null||!(password.equals(user.getPassword()))) return "0";
        if(user.getIs_admin()!=1) return "1";
        HttpSession session=ServletActionContext.getRequest().getSession();
        session.setAttribute("userid",user.getUserId());
        session.setAttribute("username",user.getUsername());
        session.setAttribute("isadmin","true");
        return "2";
    }
}
