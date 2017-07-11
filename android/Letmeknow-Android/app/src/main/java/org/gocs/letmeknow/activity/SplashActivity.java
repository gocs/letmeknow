package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.model.local.PersistableUser;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.couchdb.DBWrapper;
import org.gocs.letmeknow.couchdb.DBService;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dynamicheart on 7/3/2017.
 */

public class SplashActivity extends BaseActivity {

    private final int STR_SPLASH_TIME = 2000;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DBWrapper db = DBService.getService("testdb",App.getInstance());
        //String docID = App.getCouchDB().test_create();
        //Map<String, Object> resultByDocId = App.getCouchDB().read(docID);
        List<Object> resultByGid = App.getCouchDB().getDocByGroupId("6666");
        List<Object> resultBySid = App.getCouchDB().getDocBySenderId("5555");
        List<Object> resultByRid = App.getCouchDB().getDocByReceiverId("2333");
        startSplashTimer();
    }

    private void startSplashTimer(){
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent;
                    PersistableUser user = UserManager.getCurrentUser();
                    if(user != null && user.isLogin()){
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    }else{
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }, STR_SPLASH_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
