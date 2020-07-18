package com.vel.mvvmretrofitrvwithdagger.ui.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vel.mvvmretrofitrvwithdagger.ui.data.dao.NewsDao;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract NewsDao newsDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "news-database")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}