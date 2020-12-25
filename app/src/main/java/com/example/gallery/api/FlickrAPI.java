package com.example.gallery.api;


import com.example.gallery.model.Gallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=6287c9114b6e765132c9e6ed66fe6b10&extras=url_s&format=json&nojsoncallback=1")
    Call<Gallery> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=6287c9114b6e765132c9e6ed66fe6b10&extras=url_s&format=json&nojsoncallback=1")
    Call<Gallery> getSearchPhotos(@Query("text") String keyWord);

}
