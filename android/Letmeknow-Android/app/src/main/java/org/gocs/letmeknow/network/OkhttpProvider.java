package org.gocs.letmeknow.network;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.gocs.letmeknow.application.App;

import okhttp3.OkHttpClient;

/**
 * Created by dynamicheart on 7/3/2017.
 */

public class OkhttpProvider {

    private static OkHttpClient client;

    public static OkHttpClient getInstance(){
        if (client == null){
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getSharedPrefsCookie()));

            client = new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .build();
        }

        return client;
    }

}
