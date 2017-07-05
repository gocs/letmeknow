package service;

import model.Reply;
import model.User;

/**
 * Created by admin on 2017/6/27.
 */
public interface LoginService {
    public Reply login(User user);
    public Reply register(User user);
    public String adminLogin(String username, String password);
}
