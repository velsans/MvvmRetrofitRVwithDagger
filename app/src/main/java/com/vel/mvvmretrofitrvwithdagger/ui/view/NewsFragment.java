package com.vel.mvvmretrofitrvwithdagger.ui.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vel.mvvmretrofitrvwithdagger.R;
import com.vel.mvvmretrofitrvwithdagger.databinding.NewsFragmentBinding;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.view.adapter.NewsRecyclerAdapter;
import com.vel.mvvmretrofitrvwithdagger.ui.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements LifecycleOwner {

    NewsViewModel mViewModel;
    View news_rootview;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    NewsFragmentBinding newsFragBinding;
    SwipeRefreshLayout newsRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        newsFragBinding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        newsFragBinding.setLifecycleOwner(this);
        news_rootview = newsFragBinding.getRoot();
        this.mViewModel = new NewsViewModel(getActivity(), new ArrayList<News>(), new NewsRecyclerAdapter());
        newsFragBinding.setNewsModel(mViewModel);
        intialization();
        newsRefreshLayout.setOnRefreshListener(() -> {
            newsRefreshLayout.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                newsRefreshLayout.setRefreshing(false);
                this.mViewModel.reloadData();
            }, 1000);
        });

        return news_rootview;
    }

    private void intialization() {
        newsRefreshLayout = news_rootview.findViewById(R.id.newsSwipeLayout);
        newsRefreshLayout.setColorScheme(R.color.red, R.color.green, R.color.colorPrimaryDark, android.R.color.holo_orange_light);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.reloadData();
    }
}