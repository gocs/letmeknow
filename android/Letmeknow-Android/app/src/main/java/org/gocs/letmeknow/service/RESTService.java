package org.gocs.letmeknow.service;


import org.gocs.letmeknow.model.Circle;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.model.common.HttpResponse;
import org.gocs.letmeknow.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public interface RESTService {
    @POST("letmeknow/login")
    Observable<HttpResponse<User>> login(@Query("username") String username, @Query("password") String password);

    @POST("letmeknow/register")
    Observable<HttpResponse<User>> register(@Query("username") String username, @Query("password") String password);

    @GET("letmeknow/logout")
    Observable<HttpResponse<Void>> logout();

    @GET("letmeknow/common/allGroups")
    Observable<HttpResponse<List<CircleBrief>>> getCircles();

    @GET("letmeknow/common/groupDetail")
    Observable<HttpResponse<Circle>> getCircleDetail(@Query("groupId") String groupId);

    @GET("letmeknow/common/groupMember")
    Observable<HttpResponse<List<Member>>> getCircleMembers(@Query("groupId") String groupId);

    @GET("letmeknow/common/groupNotifier")
    Observable<HttpResponse<List<Member>>> getCircleInformers(@Query("groupId") String groupId);

    @GET("letmeknow/common/updateGroupName")
    Observable<HttpResponse<Void>> updateCircleName(@Query("groupId") String groupId, @Query("groupName") String name);

    @GET("letmeknow/common/updateGroupIntroduction")
    Observable<HttpResponse<Void>> updateCircleIntro(@Query("groupId") String groupId, @Query("introduction") String intro);

    @GET("letmeknow/common/updateUserInstallationId")
    Observable<HttpResponse<List<Void>>> updateInstallationId(@Query("userId") String userId, @Query("installationId") String installationId);

    @GET("letmeknow/common/quitGroup")
    Observable<HttpResponse<Void>> quitGroup(@Query("groupId") String groupId);

    @GET("letmeknow/common/joinGroup")
    Observable<HttpResponse<Void>> joinGroup(@Query("groupId") String groupId);

    @GET("letmeknow/common/kickOutFromGroup")
    Observable<HttpResponse<Void>> kickOut(@Query("groupId") String groupId, @Query("userId") String userId);

    @GET("letmeknow/common/changeToNotifier")
    Observable<HttpResponse<Void>> designate(@Query("groupId") String groupId, @Query("userId") String userId);
}
