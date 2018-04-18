package com.example.alexandermelnikov.yandexgallerytask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alexandermelnikov.yandexgallerytask.R;
import com.example.alexandermelnikov.yandexgallerytask.model.api.Image;

import java.nio.file.attribute.FileAttribute;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AlexMelnikov on 18.04.18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GallaryViewHolder> {

    private Context mContext;
    private List<Image> images;

    public GalleryAdapter(Context mContext, List<Image> images) {
        this.mContext = mContext;
        this.images = images;
    }

    public class GallaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView iv_thumbnail;

        public GallaryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public GallaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GallaryViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_thumbnail, parent, false));
    }

    @Override
    public void onBindViewHolder(GallaryViewHolder holder, int position) {
        Image image = images.get(position);
        Glide.with(mContext).load(image.getDisplay_sizes().get(0).uri)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_thumbnail);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void replaceData(List<Image> translations) {
        images.clear();
        images.addAll(translations);
        notifyDataSetChanged();
    }

}