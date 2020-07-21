package com.vel.mvvmretrofitrvwithdagger.ui.data.api;

import com.vel.mvvmretrofitrvwithdagger.ui.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(ServiceURL.securityControllorName + "/GetApplicationUpdateUrl")
    Call<News> GetAllNews();
}
