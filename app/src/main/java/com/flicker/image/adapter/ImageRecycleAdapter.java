package com.flicker.image.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flicker.image.R;
import com.flicker.image.model.TagListPhotos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageRecycleAdapter extends RecyclerView.Adapter<ImageRecycleAdapter.ViewHolder> {
    private List<TagListPhotos> tagImageList;
    private TagImageOnclick tagImageOnclick;

    public ImageRecycleAdapter(List<TagListPhotos> tagImageList, TagImageOnclick tagImageOnclick) {
        this.tagImageList = tagImageList;
        this.tagImageOnclick = tagImageOnclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String completeUrl = "https://farm" + tagImageList.get(position).getFarm() + ".staticflickr.com/" + tagImageList.get(position).getServer() + "/" + tagImageList.get(position).getId() + "_" + tagImageList.get(position).getSecret() + ".jpg";
        //Log.d("TAG", completeUrl);
        Picasso.get().load(completeUrl).placeholder(R.drawable.flickerimage).into(holder.tagImage);
        holder.tagTitle.setText(tagImageList.get(position).getTitle());
        holder.tagImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagImageOnclick.onClickImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tagImage;
        private TextView tagTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagImage = itemView.findViewById(R.id.tag_image);
            tagTitle = itemView.findViewById(R.id.tag_title);
        }
    }
    public interface TagImageOnclick
    {
        void onClickImage(int position);
    }
}
