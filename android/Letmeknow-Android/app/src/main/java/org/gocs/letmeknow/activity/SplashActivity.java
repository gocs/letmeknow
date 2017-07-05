package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;

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
        startSplashTimer();
    }

    private void startSplashTimer(){
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent;
                    SharedPreferences sharedPreferencesLoginStatus = getSharedPreferences(SHARED_PREFS_LOGIN_STATUS,MODE_PRIVATE);
                    if(sharedPreferencesLoginStatus.getBoolean("login_status", false)){
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
