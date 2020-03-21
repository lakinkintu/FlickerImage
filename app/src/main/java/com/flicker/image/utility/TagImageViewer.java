package com.flicker.image.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.flicker.image.R;
import com.flicker.image.adapter.TagViewPager;
import com.flicker.image.model.TagListPhotos;

import java.util.List;


public class TagImageViewer extends Dialog {
    private TagViewPager tagViewPager;
    private ViewPager viewPager;
    private List<TagListPhotos> tagImageList;
    private int position;

    public TagImageViewer(@NonNull Context context, List<TagListPhotos> tagImageList,int position) {
        super(context,android.R.style.TextAppearance_Theme_Dialog);
        this.tagImageList = tagImageList;
        this.position=position;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tag_image_viewer);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewPager = findViewById(R.id.viewPager);
        tagViewPager = new TagViewPager(tagImageList);
        viewPager.setAdapter(tagViewPager);
        viewPager.setCurrentItem(position);
    }
}
