package com.example.adrianduarte.androidchallenge.api;

import com.example.adrianduarte.androidchallenge.models.DataComment;
import com.example.adrianduarte.androidchallenge.models.DataImage;
import com.example.adrianduarte.androidchallenge.models.DataTag;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("3/tags")
    Call<DataTag> getTags();

    @GET("3/gallery/t/{tagName}")
    Call<DataImage> getTagImages(@Path("tagName") String tagName);

    @GET("3/gallery/{imageId}/comments")
    Call<DataComment> getImageComments(@Path("imageId") String imageId);

}