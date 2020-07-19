package com.vel.mvvmretrofitrvwithdagger.ui.data.databinding;


import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.view.adapter.NewsRecyclerAdapter;

import java.util.List;

public class RecyclerViewDataBinding
{
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, NewsRecyclerAdapter adapter, List<News> newsList)
    {
        recyclerView.setAdapter(adapter);
        adapter.setNewsList(newsList);
    }
}