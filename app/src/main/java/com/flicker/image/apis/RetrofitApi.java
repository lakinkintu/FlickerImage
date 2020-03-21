package com.flicker.image.apis;

import com.flicker.image.utility.UtilityConstant;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    static Retrofit retrofit = new Retrofit.Builder().baseUrl(UtilityConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

    public static TagImageService tagImageService = retrofit.create(TagImageService.class);
}
