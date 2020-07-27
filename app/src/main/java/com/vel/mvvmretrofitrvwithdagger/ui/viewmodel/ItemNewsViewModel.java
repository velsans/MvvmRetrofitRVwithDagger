package com.vel.mvvmretrofitrvwithdagger.ui.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vel.mvvmretrofitrvwithdagger.R;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;


public class ItemNewsViewModel extends BaseObservable {
    private News news;
    private Context context;


    public ItemNewsViewModel(Context context, News news) {
        this.news = news;
        this.context = context;
    }

    public String getTitle() {
        return this.news.getNews_title();
    }


    public String getDescription() {
        return this.news.getNews_description();
    }

    public String getNewsImageurl() {
        return this.news.getNews_imageurl();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (!isNullOrEmpty(url)) {
            //Glide.with(imageView.getContext()).load(url).into(imageView);
            Glide.with(imageView.getContext()).load(url).apply(new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()).into(imageView);
        }
    }

    public void setNews(News news) {
        this.news = news;
        notifyChange();
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }
}