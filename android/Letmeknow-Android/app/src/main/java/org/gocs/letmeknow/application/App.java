package org.gocs.letmeknow.application;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;

import android.support.multidex.MultiDexApplication;

import org.gocs.letmeknow.activity.MainActivity;
import org.gocs.letmeknow.couchbase.DataBaseClient;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.event.UserLoginEvent;
import org.gocs.letmeknow.util.event.UserLogoutEvent;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserLoginEvent(UserLoginEvent event){
        List<String> channels = new ArrayList<>();
        channels.add(UserManager.getCurrentUser().getUserId());
        DataBaseClient.startReplication(channels);
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    // 关联  installationId 到用户表等操作……
                    RetrofitClient.getService().updateInstallationId(UserManager.getCurrentUser().getUserId(), installationId)
                            .flatMap(NetworkErrorHandler.ErrorFilter)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                PushService.setDefaultPushCallback(getInstance(), MainActivity.class);
                            },NetworkErrorHandler.basicErrorHandler);
                } else {
                    ToastUtils.showShortToast("推送功能出错");
                }
            }
        });

        ToastUtils.showShortToast("fuck login");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserLogoutEvent(UserLogoutEvent event){
        RetrofitClient.getService().updateInstallationId(UserManager.getCurrentUser().getUserId(), null)
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    PushService.setDefaultPushCallback(getInstance(), MainActivity.class);
                },NetworkErrorHandler.basicErrorHandler);
        ToastUtils.showShortToast("fuck logout");
        DataBaseClient.stopReplication();
    }


}
