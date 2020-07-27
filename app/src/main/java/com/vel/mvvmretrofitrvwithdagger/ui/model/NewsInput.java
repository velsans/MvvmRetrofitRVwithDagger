package com.vel.mvvmretrofitrvwithdagger.ui.model;

import java.util.ArrayList;
import java.util.List;

public class NewsInput {

    String title;
    List<NewsDetails> rows=new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewsDetails> getRows() {
        return rows;
    }

    public void setRows(List<NewsDetails> rows) {
        this.rows = rows;
    }
}
