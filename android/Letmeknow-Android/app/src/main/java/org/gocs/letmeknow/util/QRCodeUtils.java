package org.gocs.letmeknow.util;

/**
 * Created by lenovo on 2017/7/18.
 */

public class QRCodeUtils {

    public static String encodeQR(String groupId){
        return  "group/" + groupId;
    }

    public static String decodeQR(String qrStr){
        String[] list = qrStr.split("/");
        return list[1];
    }
}
