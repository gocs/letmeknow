package org.gocs.letmeknow.util.manager.network;

import org.gocs.letmeknow.service.RESTService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://106.15.179.41:8080/";

    private static Retrofit client;

    private static RESTService service;

    public static RESTService getService(){

        if(service == null){
            service = getClient().create(RESTService.class);
        }
        return service;
    }

    private static Retrofit getClient(){
        if(client == null){
            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpProvider.getInstance())
                    .build();
        }
        return client;
    }
}
