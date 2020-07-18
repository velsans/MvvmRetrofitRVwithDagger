package com.vel.mvvmretrofitrvwithdagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vel.mvvmretrofitrvwithdagger.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}