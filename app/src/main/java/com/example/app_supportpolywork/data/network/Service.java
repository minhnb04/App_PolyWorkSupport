package com.example.app_supportpolywork.data.network;


import com.example.app_supportpolywork.data.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {


    @FormUrlEncoded
    @POST("/api/account/register")
    Call<ResponseBody> register(
            @Field("user_name") String userName,
            @Field("password") String password,
            @Field("full_name") String fullName,
            @Field("email") String email
    );


    @FormUrlEncoded
    @POST("api/account/login")
    Call<ResponseBody> login(
            @Field("user_name") String userName,
            @Field("password") String password
    );


    @PUT("api/account/{userId}")
    Call<ResponseBody> changeInfo(
            @Body User user,
            @Path("userId") String userId
    );

    @GET("api/account/{userId}")
    Call<ResponseBody> infoUser(
            @Path("userId") String userId
    );

    @FormUrlEncoded
    @POST("api/account/{userId}")
    Call<ResponseBody> changePassword(
            @Field("user_name") String userName,
            @Field("password") String password,
            @Field("new_password") String newPassword,
            @Path("userId") String userId
    );


    @FormUrlEncoded
    @POST("/api/documentCV/")
    Call<ResponseBody> postCV(
            @Field("document_name") String title,
            @Field("document_link") String image,
            @Field("user_id") String userId
    );

    @GET("/api/documentCV/findByUser/{userId}")
    Call<ResponseBody> getCVListOfUser(@Path("userId") String userId);

    @FormUrlEncoded
    @PUT("/api/documentCV/{cvId}")
    Call<ResponseBody> updateCV(
            @Field("document_name") String title,
            @Path("cvId") String userId
    );


    @DELETE("/api/documentCV/{cvId}")
    Call<ResponseBody> deleteCV(
            @Path("cvId") String userId
    );

    @GET("/api/documentCV/{cvId}")
    Call<ResponseBody> getCV(@Path("cvId") String userId);

    @GET("/api/job")
    Call<ResponseBody> getJob();

    @FormUrlEncoded
    @POST("/api/userJob")
    Call<ResponseBody> applyJob(
            @Field("user_id") String userId,
            @Field("cv_id") String cvId,
            @Field("job_id") String jobId
    );


}
