package org.gocs.letmeknow.network;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.gocs.letmeknow.application.App;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class OkHttpProvider {

    private static OkHttpClient client;

    public static OkHttpClient getInstance(){
        if(client == null){
            client = new OkHttpClient.Builder()
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance())))
                    .addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .build();
        }
        return client;
    }

}
