package com.flicker.image.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.flicker.image.R;
import com.flicker.image.model.TagListPhotos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TagViewPager extends PagerAdapter {
    private List<TagListPhotos> tagImageList;

    public TagViewPager(List<TagListPhotos> tagImageList) {
        this.tagImageList = tagImageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.tag_pager_adapter, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        String completeUrl = "https://farm" + tagImageList.get(position).getFarm() + ".staticflickr.com/" + tagImageList.get(position).getServer() + "/" + tagImageList.get(position).getId() + "_" + tagImageList.get(position).getSecret() + ".jpg";
        Picasso.get().load(completeUrl).placeholder(R.drawable.flickerimage).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return tagImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
