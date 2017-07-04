package org.gocs.letmeknow.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public interface ApiService {
    @POST("letmeknow/login")
    Call<Void> login(@Query("username") String username, @Query("password") String password);
}
