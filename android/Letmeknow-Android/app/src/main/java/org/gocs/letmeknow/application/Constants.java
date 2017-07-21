package org.gocs.letmeknow.application;

import org.gocs.letmeknow.R;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class Constants {
    public static final int[] TAB_IMAGE_RES_ID = {
            R.drawable.ic_tab_notifications,
            R.drawable.ic_tab_read,
            R.drawable.ic_tab_send,
            R.drawable.ic_tab_circle,
            R.drawable.ic_tab_message };

    public static final int[] CIRCLE_TAB_IMAGE_ID = {
            R.string.circle_info_intro,
            R.string.circle_info_informer,
            R.string.circle_info_members};

    public static final int TAB_NUM = 5;
    public static final int CIRCLE_TAB_NUM = 3;
    public static final int RESULT_LOAD_IMAGE = 6;

    public static final String INSTALLATION_ID = "INSTALLATION_ID";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String USER_AVATAR = "USER_AVATAR";

    public static final String JSON_KEY_USER = "user";
    public static final String JSON_KEY_CIRCLES = "groups";
    public static final String JSON_KEY_MEMBERS = "groupMember";
    public static final String OPERATION_KICK = "KICK";
    public static final String OPERATION_DESIGNATE = "DESIGNATE";

    static final String APP_ID = "nKnmFRBCNvLX5WuKyTacpA8z-gzGzoHsz";
    static final String APP_KEY = "4vMC3eTbD3KhBCO5Mmz4Wtg5";

    public static final String DEFAULT_CONVERSATION_NAME = "DEFAULT_CONVERSATION";

    public static final String COUCHDB_NAME = "testdb";

    public static final String BASE_IMG_URL = "http://106.15.179.41:8080/letmeknow/";
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;


    private static final String LEANMESSAGE_CONSTANTS_PREFIX = "com.leancloud.im.guide";

    public static final String MEMBER_ID = getPrefixConstant("member_id");
    public static final String CONVERSATION_ID = getPrefixConstant("conversation_id");

    public static final String ACTIVITY_TITLE = getPrefixConstant("activity_title");


    public static final String SQUARE_CONVERSATION_ID = "55cd829e60b2b52cda834469";

    private static String getPrefixConstant(String str) {
        return LEANMESSAGE_CONSTANTS_PREFIX + str;
    }

}
