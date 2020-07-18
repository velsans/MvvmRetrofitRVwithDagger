package com.vel.mvvmretrofitrvwithdagger.ui.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "student", indices = {@Index(value = "news_id", unique = true)} )
public class News {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Roll_No")
    private int roll_no;
    @ColumnInfo(name = "NewsID")
    private int news_id;
    @ColumnInfo(name = "Title")
    private String news_title;
    @ColumnInfo(name = "Description")
    private String news_description;
    @ColumnInfo(name = "NewsImageURL")
    private String news_imageurl;

    public News(int news_id, String news_title, String news_description, String news_imageurl) {
        this.news_id = news_id;
        this.news_title = news_title;
        this.news_description = news_description;
        this.news_imageurl = news_imageurl;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_description() {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public String getNews_imageurl() {
        return news_imageurl;
    }

    public void setNews_imageurl(String news_imageurl) {
        this.news_imageurl = news_imageurl;
    }
}
