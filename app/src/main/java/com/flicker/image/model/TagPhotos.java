package com.flicker.image.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagPhotos {
    @SerializedName("page")
    private Integer page;
    @SerializedName("pages")
    private Integer pages;
    @SerializedName("perpage")
    private Integer perpage;
    @SerializedName("total")
    private String total;
    @SerializedName("photo")
    private List<TagListPhotos> tagListPhotos = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<TagListPhotos> getTagListPhotos() {
        return tagListPhotos;
    }

    public void setTagListPhotos(List<TagListPhotos> tagListPhotos) {
        this.tagListPhotos = tagListPhotos;
    }
}
