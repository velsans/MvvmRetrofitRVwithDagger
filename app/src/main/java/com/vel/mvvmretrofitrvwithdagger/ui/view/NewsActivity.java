package com.vel.mvvmretrofitrvwithdagger.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vel.mvvmretrofitrvwithdagger.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsFragment.newInstance())
                    .commitNow();
        }
    }
}