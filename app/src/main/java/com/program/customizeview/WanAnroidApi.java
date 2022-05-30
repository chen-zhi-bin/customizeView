package com.program.customizeview;

import com.program.customizeview.domain.WanAndroidBanner;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WanAnroidApi {
    @GET("banner/json")
    Call<WanAndroidBanner> getBanner();
}
