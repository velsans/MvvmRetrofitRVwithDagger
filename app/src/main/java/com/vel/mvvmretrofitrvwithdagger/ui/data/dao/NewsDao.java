package com.vel.mvvmretrofitrvwithdagger.ui.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vel.mvvmretrofitrvwithdagger.ui.model.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM NEWS")
    List<News> getAll();

    @Query("SELECT COUNT(*) FROM NEWS")
    int count();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(News student);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(List<News> students);

    @Delete
    void delete(News student);
}
