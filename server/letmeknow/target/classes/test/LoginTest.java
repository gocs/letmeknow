package test;

import util.HttpUtil;

/**
 * Created by admin on 2017/6/29.
 */
public class LoginTest {
    public static void main(String[] args){
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/login","username=陈世昊&password=bbb"));
    }
}
