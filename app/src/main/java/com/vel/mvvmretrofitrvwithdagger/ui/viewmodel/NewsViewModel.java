package com.vel.mvvmretrofitrvwithdagger.ui.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;

import com.vel.mvvmretrofitrvwithdagger.BR;
import com.vel.mvvmretrofitrvwithdagger.ui.data.DatabaseInitializer;
import com.vel.mvvmretrofitrvwithdagger.ui.data.api.ApiClient;
import com.vel.mvvmretrofitrvwithdagger.ui.data.api.ApiInterface;
import com.vel.mvvmretrofitrvwithdagger.ui.data.db.AppDatabase;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.view.adapter.NewsRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends BaseObservable {

    private Context context;

    public ObservableInt progress;
    public ObservableInt recycler;
    ApiInterface ClientInfoApi;


    private List<News> newsList;
    private News news;
    private NewsRecyclerAdapter adapter;
    private int UniqueID = 0;

    NewsViewModel(Context context) {
        this.context = context;
        this.progress = new ObservableInt(View.VISIBLE);
        this.recycler = new ObservableInt(View.GONE);
        //this.isLoading=new ObservableBoolean(true);
    }

    public NewsViewModel(Context context, News news) {
        this(context);
        this.news = news;
    }

    public NewsViewModel(Context context, List<News> newsList, NewsRecyclerAdapter adapter) {
        this(context);
        this.newsList = newsList;
        this.adapter = adapter;
    }

    public void reloadData() {
        this.changeStudentDataSet(DatabaseInitializer.getAllNews(AppDatabase.getAppDatabase(context)));
    }

    private void changeStudentDataSet(List<News> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);

        new Handler().post(() -> {
            progress.set(View.GONE);
            recycler.set(View.VISIBLE);
            notifyPropertyChanged(BR.newsList);
        });
    }

    @Bindable
    public List<News> getNewsList() {
        return newsList;
    }

    @Bindable
    public NewsRecyclerAdapter getAdapter() {
        return this.adapter;
    }

    public void onClickFabLoad(View view) {
        ClientInfoApi = ApiClient.getApiInterface();
        ClientInfoApi.GetAllNews().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
        //isLoading.set(true);
        UniqueID = UniqueID + 1;
        //news = new News(UniqueID, "News14", "fake", "https://androidwave.com/wp-content/uploads/2019/01/profile_pic.jpg");
        news = new News(UniqueID, "News14", "fake", "https://randomuser.me/api/portraits/thumb/women/68.jpg");
        if (DatabaseInitializer.insertNews(AppDatabase.getAppDatabase(context), news) != -1) {
            Toast.makeText(view.getContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
            reloadData();
            //isLoading.set(false);
            return;
        }
        Toast.makeText(view.getContext(), "Failed to Insert", Toast.LENGTH_SHORT).show();
    }
}