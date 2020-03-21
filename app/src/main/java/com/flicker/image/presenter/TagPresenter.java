package com.flicker.image.presenter;

import com.flicker.image.model.TagListPhotos;
import com.flicker.image.model.apicallback.ModelApiCallback;
import com.flicker.image.view.TagView;

import java.util.List;

public class TagPresenter implements ModelApiCallback.ResultCallBack {
    private TagView tagView;
    private ModelApiCallback modelApiCallback;

    public TagPresenter(TagView tagView) {
        this.tagView = tagView;
        modelApiCallback = new ModelApiCallback();
    }

    public void callApi(String tagName, int pageNo) {
        modelApiCallback.searchTag(tagName, pageNo, this::result);
    }

    @Override
    public void result(List<TagListPhotos> tagImageList) {
        tagView.tagResulet(tagImageList);
    }
}
