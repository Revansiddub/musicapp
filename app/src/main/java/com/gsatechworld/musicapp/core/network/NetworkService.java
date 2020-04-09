package com.gsatechworld.musicapp.core.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private static Retrofit retrofit = null;

    /* ------------------------------------------------------------- *
     * Public Methods
     * ------------------------------------------------------------- */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiBaseURL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
