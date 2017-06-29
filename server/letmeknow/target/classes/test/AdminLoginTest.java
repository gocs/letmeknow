package test;

import util.HttpUtil;

/**
 * Created by admin on 2017/6/29.
 */
public class AdminLoginTest {
    public static void main(String[] args){
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/admin/login","username=admin&password=password"));
    }
}
