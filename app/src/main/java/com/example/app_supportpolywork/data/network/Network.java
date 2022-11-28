package com.example.app_supportpolywork.data.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public final static String STATUS_KEY = "StatusCode";
    public final static String MESSAGE_KEY = "Message";
    public final static String DATA_KEY = "Data";
    public final static String ID_KEY = "_id";
    public final static String AUTHORIZATION_KEY = "Authorization";

    private static final String BASE_URL = "http://192.168.0.101:9000/";

    private static final OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient)
            .build();

    public static Service mService = mRetrofit.create(Service.class);
}
