package com.vel.mvvmretrofitrvwithdagger.ui.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vel.mvvmretrofitrvwithdagger.R;
import com.vel.mvvmretrofitrvwithdagger.databinding.NewsFragmentBinding;
import com.vel.mvvmretrofitrvwithdagger.ui.model.News;
import com.vel.mvvmretrofitrvwithdagger.ui.view.adapter.NewsRecyclerAdapter;
import com.vel.mvvmretrofitrvwithdagger.ui.viewmodel.NewsViewModel;

import java.util.List;

public class NewsFragment extends Fragment implements LifecycleOwner {

    private NewsViewModel mViewModel;
    SwipeRefreshLayout news_refreshLay;
    View news_rootview;
    RecyclerView NewsRecyclerView;
    NewsRecyclerAdapter newsadapter;
    LinearLayoutManager horizontalLayoutManager;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    NewsFragmentBinding newsFragBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        newsFragBinding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        newsFragBinding.setLifecycleOwner(this);
        news_rootview = newsFragBinding.getRoot();
        NewsRecyclerView = newsFragBinding.newsRecylcerView;
        refresh();
        intialization();

        news_refreshLay.setOnRefreshListener(() -> {
            news_refreshLay.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                news_refreshLay.setRefreshing(false);
                refresh();
            }, 3000);
        });

        return news_rootview;
    }

    private void intialization() {
        news_refreshLay = news_rootview.findViewById(R.id.newsSwipeLayout);
        news_refreshLay.setColorScheme(R.color.red, R.color.green, R.color.colorPrimary, R.color.colorPrimaryDark);
    }

    public void refresh() {
        mViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        mViewModel.getTransaction().observe(getActivity(), newsUpdateObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.mViewModel.reloadData();
    }

    Observer<List<News>> newsUpdateObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> newsList) {
            newsadapter = new NewsRecyclerAdapter(getActivity(), newsList);
            horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            NewsRecyclerView.setLayoutManager(horizontalLayoutManager);
            newsadapter.notifyDataSetChanged();
            NewsRecyclerView.setAdapter(newsadapter);
        }
    };
}