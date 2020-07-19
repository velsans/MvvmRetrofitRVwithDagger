package com.vel.mvvmretrofitrvwithdagger.ui;

import android.app.Application;
import androidx.databinding.DataBindingUtil;
import com.vel.mvvmretrofitrvwithdagger.ui.data.databinding.AppDataBindingComponent;

public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}
