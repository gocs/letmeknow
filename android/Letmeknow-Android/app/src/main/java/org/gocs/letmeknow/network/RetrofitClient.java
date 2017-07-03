package org.gocs.letmeknow.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.service.ApiService;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dynamicheart on 7/3/2017.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://106.15.179.41:8080/letmeknow/";

    private static ApiService apiService;

    public static ApiService getApiService(){
        if(apiService == null){
            Executor executor = Executors.newCachedThreadPool();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .callbackExecutor(executor)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(OkhttpProvider.getInstance())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
