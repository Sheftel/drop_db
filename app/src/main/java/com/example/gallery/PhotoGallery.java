package com.example.gallery;

import android.app.SearchManager;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gallery.api.FlickrAPI;
import com.example.gallery.api.ServiceAPI;
import com.example.gallery.db.PhotosDAO;
import com.example.gallery.db.PhotosDB;
import com.example.gallery.model.Photo;
import com.example.gallery.model.Gallery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class PhotoGallery extends AppCompatActivity {

    List<Photo> photos= new ArrayList<>();
    private final FlickrAPI fa = ServiceAPI.getRetrofit().create(FlickrAPI.class);
    private PhotosDAO dao;
    public final PhotoAdapter pa = new PhotoAdapter(photos);
    public static boolean isLocal = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        RecyclerView rv;
        rv = findViewById(R.id.recView);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        dao = PhotosDB.getDatabase(App.getAppContext()).photosDAO();
        fa.getRecent().enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                photos.addAll(response.body().getPhotos().getPhoto());
                pa.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<com.example.gallery.model.Gallery> call, Throwable t) {

            }
        });
        rv.setAdapter(pa);

    }

    public void switchToLocal(MenuItem m){
        isLocal=true;
        photos.clear();
        photos.addAll(dao.LoadAll());
        pa.notifyDataSetChanged();
    }

    public void switchToFlickr(MenuItem m){
        isLocal=false;
        Retrofit retrofit = ServiceAPI.getRetrofit();
        FlickrAPI fa = retrofit.create(FlickrAPI.class);
        Call<Gallery> call = fa.getRecent();
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                photos.addAll(response.body().getPhotos().getPhoto());
                pa.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {

            }
        });
    }


    protected SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            fa.getSearchPhotos(query).enqueue(new Callback<Gallery>() {
                @Override
                public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                    photos.clear();
                    photos.addAll(response.body().getPhotos().getPhoto());
                    pa.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Gallery> call, Throwable t) {

                }
            });
            return true;
        }

        @Override
        public boolean onQueryTextChange(String query) {


            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView sv = (SearchView)menu.findItem(R.id.option_flickr_search).getActionView();
        sv.setOnQueryTextListener(onQueryTextListener);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        sv.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    public static boolean GetIsLocal(){ return isLocal;}

    public void dropTheBase(MenuItem item) {
        for(Photo p : photos) {
            dao.deletePhoto(p);
        }
    }
}