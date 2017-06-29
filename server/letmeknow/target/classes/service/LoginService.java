package service;

import model.LoginForm;

/**
 * Created by admin on 2017/6/27.
 */
public interface LoginService {
    public boolean login(String username, String password);
    public boolean register(String username, String password, String email, Integer phone_num);
    public boolean adminLogin(String username, String password);
}
