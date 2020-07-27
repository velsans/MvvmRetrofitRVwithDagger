package com.vel.mvvmretrofitrvwithdagger.ui.data.api;

import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.model.NewsInput;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<NewsInput> GetAllNews();
}
