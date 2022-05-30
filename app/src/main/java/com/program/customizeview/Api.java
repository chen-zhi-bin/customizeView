package com.program.customizeview;

import com.program.customizeview.domain.ImageString;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<ImageString> getImage();
}
