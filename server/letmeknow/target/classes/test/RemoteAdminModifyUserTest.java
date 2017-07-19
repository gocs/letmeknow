package test;

import util.HttpUtil;

/**
 * Created by admin on 2017/7/3.
 */
public class RemoteAdminModifyUserTest {
    public static void main(String[] args) {
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/adminlogin", "username=admin&password=123456"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/reducePrivilege", "userId=2"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/promotePrivilege", "userId=2"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/disableUser", "userId=2"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/activateUser", "userId=2"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/deleteUser", "userId=2"));
        System.out.println(HttpUtil.sendPost("http://106.15.179.41:8080/letmeknow/promotePrivilege", "userId=10086"));
    }
}
