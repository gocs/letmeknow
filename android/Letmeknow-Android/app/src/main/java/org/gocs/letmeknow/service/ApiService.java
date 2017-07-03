package org.gocs.letmeknow.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dynamicheart on 7/3/2017.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("login")
    Observable<String> login(@Field("username") String username,
                             @Field("password") String password);

    @POST("checkLogin")
    Observable<String> checkLogin();
}
