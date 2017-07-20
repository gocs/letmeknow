package org.gocs.letmeknow.util.manager.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by lenovo on 2017/7/4.
 */

public class ConnectivityHelper {

    public static int NOT_CONNECTED = 0;
    public static int TYPE_MOBILE = 1;
    public static int TYPE_WIFI = 2;

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnected() && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static int getConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
            else return NOT_CONNECTED;
        } else {
            return NOT_CONNECTED;
        }
    }

}
