package com.flicker.image.model;

import com.google.gson.annotations.SerializedName;

public class TagImage {
    @SerializedName("photos")
    private TagPhotos photos;
    @SerializedName("stat")
    private String stat;

    public TagPhotos getPhotos() {
        return photos;
    }

    public void setPhotos(TagPhotos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
