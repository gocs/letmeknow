package org.gocs.letmeknow.application;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import android.support.multidex.MultiDexApplication;

import org.gocs.letmeknow.util.UserManager;

import static com.avos.avoscloud.AVInstallation.getCurrentInstallation;

/**
 * Created by dynamicheart on 6/26/2017.
 */

public class App extends MultiDexApplication {
    @SuppressWarnings("StaticFieldLeak")
    private static Context context;

    public static Context getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initLeanCloud();
    }

    private void initLeanCloud(){
        AVOSCloud.initialize(this, Constants.APP_ID, Constants.APP_KEY);

        AVOSCloud.setDebugLogEnabled(true);
        AVOSCloud.setLastModifyEnabled(true);
        AVAnalytics.enableCrashReport(getInstance(), true);
    }

}
