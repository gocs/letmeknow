package org.gocs.letmeknow.util.manager.network;

import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.util.CustomPersistentCookieJar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class OkHttpProvider {

    private static OkHttpClient client;

    private static CustomPersistentCookieJar persistentCookieJar;

    static OkHttpClient getInstance(){
        if(client == null){
            client = new OkHttpClient.Builder()
                    .cookieJar(getPersistentCookieJar())
                    .addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return client;
    }

    private static CustomPersistentCookieJar getPersistentCookieJar(){
        if(persistentCookieJar == null){
            persistentCookieJar = new CustomPersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance()));
        }
        return persistentCookieJar;
    }

    public static void clearCookie(){
        getPersistentCookieJar().clear();
    }

}
