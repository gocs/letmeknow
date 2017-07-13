package org.gocs.letmeknow.application;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by dynamicheart on 6/26/2017.
 */

public class App extends Application {
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
        AVInstallation.getCurrentInstallation().getInstallationId();
        AVOSCloud.setDebugLogEnabled(true);
    }

}
