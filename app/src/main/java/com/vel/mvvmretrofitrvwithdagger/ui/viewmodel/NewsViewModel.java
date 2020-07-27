package com.vel.mvvmretrofitrvwithdagger.ui.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
import com.vel.mvvmretrofitrvwithdagger.ui.model.NewsDetails;
import com.vel.mvvmretrofitrvwithdagger.ui.model.NewsInput;
import com.vel.mvvmretrofitrvwithdagger.ui.view.NewsActivity;
import com.vel.mvvmretrofitrvwithdagger.ui.view.adapter.NewsRecyclerAdapter;

import java.util.ArrayList;
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
        this.changeNewsDataSet(DatabaseInitializer.getAllNews(AppDatabase.getAppDatabase(context)));
    }

    private void changeNewsDataSet(List<News> newsList) {
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
        ClientInfoApi.GetAllNews().enqueue(new Callback<NewsInput>() {
            @Override
            public void onResponse(Call<NewsInput> call, Response<NewsInput> response) {
                if (response.body() != null) {
                    InsertRows(response.body().getRows(), view,response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<NewsInput> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void InsertRows(List<NewsDetails> newsInputDetails, View view,String Title) {
        try {
            // Action Bar Title
            ((AppCompatActivity) new NewsActivity()).getSupportActionBar().setTitle(Title);
            // Get table last row news id
            UniqueID = DatabaseInitializer.getCount(AppDatabase.getAppDatabase(context));
            // Insert All
            //DatabaseInitializer.in(AppDatabase.getAppDatabase(context), newsInputDetails);
            //Insert one by one
            // according to your json i will insert one by one cause i am not allowing duplications
            for (NewsDetails newsDetails : newsInputDetails) {
                UniqueID = UniqueID + 1;
                news = new News(UniqueID, newsDetails.getTitle(), newsDetails.getDescription(), newsDetails.getImageHref());
                DatabaseInitializer.insertNews(AppDatabase.getAppDatabase(context), news);
            }
            Toast.makeText(view.getContext(), "Inserted Sucessfully", Toast.LENGTH_SHORT).show();
            reloadData();
            UniqueID=0;
        } catch (Exception ex) {
            Toast.makeText(view.getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}