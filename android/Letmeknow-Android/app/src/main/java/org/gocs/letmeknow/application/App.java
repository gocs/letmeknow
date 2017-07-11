package org.gocs.letmeknow.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.avos.avoscloud.AVOSCloud;

import org.gocs.letmeknow.couchdb.DBWrapper;

/**
 * Created by dynamicheart on 6/26/2017.
 */

public class App extends Application {
    @SuppressWarnings("StaticFieldLeak")
    private static Context context;

    private static DBWrapper Database;

    public static Context getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initLeanCloud();
        initCouchDB();
    }

    private void initLeanCloud(){
        AVOSCloud.initialize(this, Constants.APP_ID, Constants.APP_KEY);
        AVOSCloud.setDebugLogEnabled(true);
    }

    private void initCouchDB(){
        Database = new DBWrapper("testdb",context);
    }

    public static DBWrapper getCouchDB(){
        return Database;
    }
}
