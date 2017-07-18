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

    public static final String INSTALLATION_ID = "INSTALLATION_ID";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String USER_AVATAR = "USER_AVATAR";

    public static final String JSON_KEY_USER = "user";
    public static final String JSON_KEY_CIRCLES = "groups";

    static final String APP_ID = "nKnmFRBCNvLX5WuKyTacpA8z-gzGzoHsz";
    static final String APP_KEY = "4vMC3eTbD3KhBCO5Mmz4Wtg5";

    public static final String DEFAULT_CONVERSATION_NAME = "DEFAULT_CONVERSATION";

    public enum TabStatus{
        NOTIFICATION,
        SEND,
        READ
    }

    public static final String COUCHDB_NAME = "testdb";


    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;

}
