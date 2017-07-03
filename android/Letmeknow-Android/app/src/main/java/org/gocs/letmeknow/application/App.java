package org.gocs.letmeknow.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dynamicheart on 6/26/2017.
 */

public class App extends Application {

    @SuppressWarnings("StaticFieldLeak")
    private static Context context;

    final private static String SHARED_PREFS_COOKIE = "cookie";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static SharedPreferences getSharedPrefsCookie() {
        return context.getSharedPreferences(SHARED_PREFS_COOKIE, Context.MODE_PRIVATE);
    }
}
