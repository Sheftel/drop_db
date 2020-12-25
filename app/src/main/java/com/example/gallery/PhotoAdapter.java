package com.example.gallery;

import android.view.*;
import android.widget.ImageView;

import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gallery.db.PhotosDAO;
import com.example.gallery.db.PhotosDB;
import com.example.gallery.model.*;
import com.example.gallery.PhotoGallery;
import android.content.Context;

import java.util.List;

import com.squareup.picasso.*;

public class PhotoAdapter extends RecyclerView.Adapter <PhotoAdapter.ViewHolder> {

    private OnInsertListener onInsertListener;
    private final List<Photo> photos;
    private PhotosDAO dao;
    public PhotoAdapter(List<Photo> values ) {

        photos = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView tv;
        ViewHolder(View view){
            super(view);
            picture = view.findViewById(R.id.img);
            tv =view.findViewById(R.id.textView);
            dao= PhotosDB.getDatabase(view.getContext()).photosDAO();
            picture.setOnTouchListener(new View.OnTouchListener() {
                private final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        if(PhotoGallery.GetIsLocal()) {
                            dao.deletePhoto(photos.get(ViewHolder.this.getAdapterPosition()));
                        }
                        else
                            dao.insertPhoto(photos.get(ViewHolder.this.getAdapterPosition()));
                        return super.onDoubleTap(e);
                    }
                });

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    gestureDetector.onTouchEvent(event);
                    return true;
                    }
                });
            }
        }




    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        Picasso.get().load(photo.getUrlS()).into(holder.picture);
        holder.itemView.setTag(photo);
        holder.tv.setText(photo.getTitle().substring(0,(Math.min(photo.getTitle().length(), 20))));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent,false);
        dao= PhotosDB.getDatabase(App.getAppContext()).photosDAO();
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public interface OnInsertListener {
        void OnInsert(Photo photo);
    }

    public void setOnInsertListener(OnInsertListener onInsertListener) {
        this.onInsertListener = onInsertListener;
    }
}
