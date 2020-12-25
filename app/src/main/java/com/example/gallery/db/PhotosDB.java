package com.example.gallery.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.gallery.model.Photo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class PhotosDB extends RoomDatabase{
    public abstract PhotosDAO photosDAO();

    private static volatile PhotosDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PhotosDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhotosDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhotosDB.class, "database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
