package com.example.gallery.db;

import androidx.room.*;
import com.example.gallery.model.Photo;

import java.util.List;

@Dao
public interface PhotosDAO {
    @Query("SELECT * FROM Photo")
    public List<Photo> LoadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPhoto(Photo photo);

    @Delete
    public void deletePhoto(Photo photo);
}

