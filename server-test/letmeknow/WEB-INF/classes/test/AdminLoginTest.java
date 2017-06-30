package test;

import util.HttpUtil;
import util.TestUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
public class AdminLoginTest {
    public static void main(String[] args) throws IOException {
        /*
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=admin&password=password"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=bbb&password=password"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=aaa&password=password"));
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=bbb&password=bbb"));
        */
        List<String> users= TestUtil.read("D://adminlogin.txt");
        for (String user:users){
            String params[]=TestUtil.parse(user);
            System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin",params[0]));
        }
    }
}
