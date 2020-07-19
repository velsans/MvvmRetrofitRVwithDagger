package com.vel.mvvmretrofitrvwithdagger.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vel.mvvmretrofitrvwithdagger.R;
import com.vel.mvvmretrofitrvwithdagger.databinding.ItemNewsBinding;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    Context mCtx;
    List< News>  NewsList;
    private View.OnClickListener mOnItemClickListener;

    public NewsRecyclerAdapter(Context mCtx, List< News>  NewsList) {
        this.mCtx = mCtx;
        this. NewsList =  NewsList;
    }

    @NonNull
    @Override
    public  NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_news, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsViewHolder holder, int position) {
         News newspojo =  NewsList.get(position);
        //Glide.with(mCtx).load(hero.getImageurl()).into(holder.imageView);
        holder.bind(newspojo);

    }

    public void setNewsDetials(List< News>  NewsList) {
        this. NewsList =  NewsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if ( NewsList == null) {
            return 0;
        }
        return  NewsList.size();
    }

    public class  NewsViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public  NewsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(this);
            /*Onclick Item listener*/
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public void bind( News item) {
            binding.setNewspojo(item);
        }
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

}
