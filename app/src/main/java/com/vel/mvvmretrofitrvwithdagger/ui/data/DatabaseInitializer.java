package com.vel.mvvmretrofitrvwithdagger.ui.data;

import com.vel.mvvmretrofitrvwithdagger.ui.data.db.AppDatabase;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.model.NewsDetails;

import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static long insertNews(final AppDatabase db, final News news)
    {
        return db.newsDao().insert(news);
    }

    //public static long insertAllNews(final AppDatabase db, final List<NewsDetails> newsDetails) {
    //    return db.newsDao().insertAll(newsDetails);
   // }

    public static List<News> getAllNews(final AppDatabase db)
    {
        return db.newsDao().getAll();
    }

    public static int getCount(final AppDatabase db){
        return db.newsDao().count();
    }
}
