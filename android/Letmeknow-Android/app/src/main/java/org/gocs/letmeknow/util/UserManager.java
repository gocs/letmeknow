package org.gocs.letmeknow.util;

import android.util.Log;

import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.service.ApiService;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by dynamicheart on 7/3/2017.
 */

public class UserManager {
    static private UserManager instance;
    private User user;
    private boolean login = false;

    private UserManager() {
    }

    public static UserManager getInstance() {
        return instance;
    }

    public static void init() {
        instance = new UserManager();
    }

    public boolean isLogin() {
        return login;
    }

    public User getUser() {
        return user;
    }

}
