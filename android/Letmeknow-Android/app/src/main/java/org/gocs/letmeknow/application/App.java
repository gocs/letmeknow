package org.gocs.letmeknow.application;

import android.content.Context;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import android.support.multidex.MultiDexApplication;

import org.gocs.letmeknow.activity.MainActivity;
import org.gocs.letmeknow.util.manager.couchbase.DataBaseClient;
import org.gocs.letmeknow.util.manager.leancloud.AVImClientManager;
import org.gocs.letmeknow.util.manager.network.RetrofitClient;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.manager.cache.UserManager;
import org.gocs.letmeknow.util.event.UserLoginEvent;
import org.gocs.letmeknow.util.event.UserLogoutEvent;
import org.gocs.letmeknow.util.handler.MessageHandler;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onTerminate() {
        EventBus.getDefault().unregister(this);
        super.onTerminate();
    }

    private void initLeanCloud(){
        AVOSCloud.initialize(this, Constants.APP_ID, Constants.APP_KEY);

        // 必须在启动的时候注册 MessageHandler
        // 应用一启动就会重连，服务器会推送离线消息过来，需要 MessageHandler 来处理
        AVIMMessageManager.registerMessageHandler(AVIMTypedMessage.class, new MessageHandler(this));

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
        AVImClientManager.getInstance().open(UserManager.getCurrentUser().getUserId(), new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {

            }
        });
        switch (event.getLoginType()){
            case LOGIN:
                ToastUtils.showShortToast("登录成功");
                break;
            case REGISTER:
                ToastUtils.showShortToast("注册成功");
                break;
            default:
                break;
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserLogoutEvent(UserLogoutEvent event){
        RetrofitClient.getService().updateInstallationId(UserManager.getCurrentUser().getUserId(), null)
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                },NetworkErrorHandler.basicErrorHandler);

        DataBaseClient.stopReplication();
        ToastUtils.showShortToast("注销成功");
    }


}
