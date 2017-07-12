package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.service.TempService;
import org.gocs.letmeknow.util.NetworkErrorHandler;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.couchdb.DBWrapper;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;

import static org.gocs.letmeknow.couchdb.DBWrapper.test_create;

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
//        Manager manager = DBWrapper.getManagerInstance();
//        Database database = getCouchDBInstance();
//        try{
//            database.delete();
//        }catch (Exception ignore){
//        }
        String docID = test_create();
        Object resultByDocId = DBWrapper.read(docID);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false);


        List<Object> resultByGid = DBWrapper.getDocByGroupId("1234");
        Notification Notification = objectMapper.convertValue(resultByGid.get(0),Notification.class);
        //List<Object> resultBySid = DBWrapper.getDocBySenderId("4321");
        //List<Object> resultByRid = DBWrapper.getDocByReceiverId("3212");
        TempService.listByGroupId("1234")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> {
                    Log.d("test",notifications.toString());
                    ToastUtils.showShortToast("rxjava query database succeed!");

                }, NetworkErrorHandler.basicErrorHandler);
        List<Object> resultBySid = DBWrapper.getDocBySenderId("4321");
        List<Object> resultByRid = DBWrapper.getDocByReceiverId("3212");
        startSplashTimer();
    }

    private void startSplashTimer(){
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent;
                    User user = UserManager.getCurrentUser();
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
