package org.gocs.letmeknow.service;


import org.gocs.letmeknow.model.common.HttpResponse;
import org.gocs.letmeknow.model.remote.RemoteUser;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public interface RESTService {
    @POST("letmeknow/login")
    Observable<HttpResponse<RemoteUser>> login(@Query("username") String username, @Query("password") String password);

    @POST("letmeknow/register")
    Observable<HttpResponse<RemoteUser>> register(@Query("username") String username, @Query("password") String password);
}
