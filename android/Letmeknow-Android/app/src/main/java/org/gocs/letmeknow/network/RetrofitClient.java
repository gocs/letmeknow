package org.gocs.letmeknow.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.api.LetmeknowApi;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.util.GsonDateTypeAdapter;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2017/7/4.
 */

public class RetrofitClient {

    private static final String BASE_URL = App.getInstance().getString(R.string.letmeknow_host) + "/api/";

    private static LetmeknowApi letmeknowApi;

    public LetmeknowApi getLetmeknowApi(){
        if(letmeknowApi == null){
            Executor executor = Executors.newCachedThreadPool();
            final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateTypeAdapter()).create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .callbackExecutor(executor)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //.client(OkHttpProvider.getInstance())
                    .build();

            letmeknowApi = retrofit.create(LetmeknowApi.class);
        }
        return letmeknowApi;
    }
}
