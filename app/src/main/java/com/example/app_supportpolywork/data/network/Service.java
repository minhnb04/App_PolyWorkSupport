package com.example.app_supportpolywork.data.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
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

    @FormUrlEncoded
    @PUT("api/account/{userId}")
    Call<ResponseBody> changeInfo(
            @Field("full_name") String fullName,
            @Field("email") String email,
            @Field("address") String address,
            @Field("gender") int gender,
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


}
