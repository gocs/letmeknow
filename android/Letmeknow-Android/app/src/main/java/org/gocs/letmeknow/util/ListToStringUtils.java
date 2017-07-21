package org.gocs.letmeknow.util;

import java.util.List;

/**
 * Created by lenovo on 2017/7/21.
 */

public class ListToStringUtils {

    public static String ListToString(List<String> users){
        String result = "[";
        int i = 0;
        for(i = 0; i < (users.size()-1); i++){
            result +=  users.get(i) + ",";
        }
        result += users.get(users.size()-1) + "]";
        return result;
    }

}
