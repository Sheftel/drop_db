package com.example.gallery.api;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceAPI {
    private static final String BASE_URL="https://api.flickr.com/";
    private static Retrofit retrofit = null;
    private static FlickrAPI flickrAPI;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
