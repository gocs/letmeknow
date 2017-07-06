package org.gocs.letmeknow.service;


import org.gocs.letmeknow.model.common.LMKHttpResponse;
import org.gocs.letmeknow.model.remote.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public interface LMKService {
    @POST("letmeknow/login")
    Observable<LMKHttpResponse<User>> login(@Query("username") String username, @Query("password") String password);

    @POST("letmeknow/register")
    Observable<LMKHttpResponse<User>> register(@Query("username") String username, @Query("password") String password);
}
