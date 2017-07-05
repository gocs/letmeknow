package org.gocs.letmeknow.network;

import org.gocs.letmeknow.service.LMKService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://106.15.179.41:8080/";

    private static Retrofit client;

    private static LMKService service;

    public static LMKService getService(){
        if(client == null){
            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpProvider.getInstance())
                    .build();
        }
        if(service == null){
            service = client.create(LMKService.class);
        }
        return service;
    }



}
