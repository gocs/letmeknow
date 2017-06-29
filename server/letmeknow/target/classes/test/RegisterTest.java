package test;

import util.HttpUtil;

/**
 * Created by admin on 2017/6/29.
 */
public class RegisterTest {
    public static void main(String[] args){
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/register","username=bbb&password=bbb"));
    }
}
