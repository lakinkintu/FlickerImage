package com.flicker.image.apis;



import com.flicker.image.model.TagImage;
import com.flicker.image.utility.UtilityConstant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TagImageService {
    @GET(UtilityConstant.METHOD)
    Call<TagImage> TAG_IMAGE_CALL(
            @Query("api_key") String apikey,
            @Query("format") String format,
            @Query("nojsoncallback") int nojsoncallback,
            @Query("tags") String tags,
            @Query("page") int page,
            @Query("per_page") int per_page);
}
