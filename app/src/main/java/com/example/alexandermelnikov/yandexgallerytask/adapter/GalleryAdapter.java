package com.example.alexandermelnikov.yandexgallerytask.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alexandermelnikov.yandexgallerytask.R;
import com.example.alexandermelnikov.yandexgallerytask.model.api.Photo;
import com.example.alexandermelnikov.yandexgallerytask.model.realm.ImageSrc;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AlexMelnikov on 18.04.18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GallaryViewHolder> {

    private static final String TAG = "MyTag";
    
    private Context mContext;
    private ArrayList<ImageSrc> sources;
    private OnGalleryItemClickListener listener;

    public GalleryAdapter(Context mContext, ArrayList<ImageSrc> sources, OnGalleryItemClickListener listener) {
        this.mContext = mContext;
        this.sources = sources;
        this.listener = listener;
    }

    public class GallaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView iv_thumbnail;

        public GallaryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnGalleryItemClickListener {
        void onGalleryItemClicked(int poition, ImageView sharedImageView);
    }

    @Override
    public GallaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GallaryViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_thumbnail, parent, false));
    }

    @Override
    public void onBindViewHolder(GallaryViewHolder holder, int position) {
        ImageSrc source = sources.get(position);
        Glide.with(mContext).load(source.getThumbnailUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_thumbnail);

        RxView.clicks(holder.iv_thumbnail)
                .subscribe(v -> listener.onGalleryItemClicked(holder.getAdapterPosition(), holder.iv_thumbnail));
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public void replaceData(ArrayList<ImageSrc> sourceList) {
        sources.clear();
        sources.addAll(sourceList);
        notifyDataSetChanged();
    }

    public void clearData() {
        sources.clear();
        notifyDataSetChanged();
    }

}
