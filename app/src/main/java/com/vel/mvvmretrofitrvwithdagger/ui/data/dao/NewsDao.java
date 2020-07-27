package com.vel.mvvmretrofitrvwithdagger.ui.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.model.NewsDetails;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM NEWS")
    List<News> getAll();

    //@Query("SELECT COUNT(*) FROM NEWS")
    @Query("SELECT NewsID FROM NEWS ORDER BY NewsID DESC LIMIT 1")
    int count();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(News  news);

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //long[] insertAll(List<NewsDetails> newss);

    @Delete
    void delete(News  news);
}
