package test;

import util.HttpUtil;

/**
 * Created by admin on 2017/7/3.
 */
public class AdminModifyUserTest {
    public static void main(String[] args){
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=bbb&password=bbb"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/reducePrivilege","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/promotePrivilege","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/disableUser","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/activateUser","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/deleteUser","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/restoreUser","userId=1"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/promotePrivilege","userId=10086"));

    }
}
