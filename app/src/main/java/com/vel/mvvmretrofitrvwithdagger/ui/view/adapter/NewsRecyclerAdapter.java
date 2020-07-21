package com.vel.mvvmretrofitrvwithdagger.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vel.mvvmretrofitrvwithdagger.R;
import com.vel.mvvmretrofitrvwithdagger.databinding.ItemNewsBinding;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.viewmodel.ItemNewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    private List<News> NewsList;

    private View.OnClickListener mOnItemClickListener;

    public NewsRecyclerAdapter() {
        this.NewsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding itenBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_news, parent, false);

        return new NewsViewHolder(itenBinding);
    }

    public News getNews(int position) {
        return NewsList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bindNews(NewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public void setNewsList(List<News> newsList) {
        this.NewsList.clear();
        this.NewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        void bindNews(News news) {
            if (binding.getNewsitemModel() == null) {
                binding.setNewsitemModel(new ItemNewsViewModel(itemView.getContext(), news));
            } else {
                binding.getNewsitemModel().setNews(news);
            }
        }
    }

}
