package com.flicker.image.model.apicallback;

import com.flicker.image.apis.RetrofitApi;
import com.flicker.image.model.TagImage;
import com.flicker.image.model.TagListPhotos;
import com.flicker.image.utility.UtilityConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface ModelCallback {
    void searchTag(String tagname, int pageNo,ResultCallBack resultCallBack);

    interface ResultCallBack {
        void result(List<TagListPhotos> tagImageList);
    }
}

public class ModelApiCallback implements ModelCallback {
    @Override
    public void searchTag(String tagname, int pageNo, ResultCallBack resultCallBack) {
        Call<TagImage> tagImageCall = RetrofitApi.tagImageService.TAG_IMAGE_CALL(
                UtilityConstant.API_KEY, UtilityConstant.FORMAT, UtilityConstant.NOJSONCALLBACK, tagname, pageNo, UtilityConstant.PER_PAGE);
        tagImageCall.enqueue(new Callback<TagImage>() {
            @Override
            public void onResponse(Call<TagImage> call, Response<TagImage> response) {
                if (response.code() == 200) {
                    resultCallBack.result(response.body().getPhotos().getTagListPhotos());
                }
            }

            @Override
            public void onFailure(Call<TagImage> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
