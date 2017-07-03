package test;

import util.HttpUtil;
import util.TestUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public class PrivilegeTest {
    public static void main(String[] args) throws IOException {
        System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/adminlogin","username=bbb&password=bbb"));
        List<String> users= TestUtil.read("D://privilege.txt");
        for (String user:users){
            String params[]=TestUtil.parse(user);
            System.out.println(HttpUtil.sendPost("http://localhost:8080/letmeknow/promotePrivilege",params[0]));
        }

    }
}
