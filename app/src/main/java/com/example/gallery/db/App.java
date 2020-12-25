package com.example.gallery.db;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    public static App instance;

    private PhotosDB database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, PhotosDB.class, "database").allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public PhotosDB getDatabase() {
        return PhotosDB.getDatabase(getApplicationContext());
    }
}
