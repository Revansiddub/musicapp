package com.gsatechworld.musicapp.core.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;
    private static int REQUEST_TIMEOUT = 20;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiBaseURL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesClient())
                    .build();
        }

        return retrofit;
    }

    static OkHttpClient providesClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }
}

