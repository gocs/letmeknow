package service;

/**
 * Created by admin on 2017/6/27.
 */
public interface LoginService {
    public boolean login(String username, String password);
    public int register(String username, String password, String email, Integer phone_num);
    public String adminLogin(String username, String password);
}
