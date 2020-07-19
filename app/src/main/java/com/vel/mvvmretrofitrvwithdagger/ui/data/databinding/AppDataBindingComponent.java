package com.vel.mvvmretrofitrvwithdagger.ui.data.databinding;

public class AppDataBindingComponent implements androidx.databinding.DataBindingComponent {

    @Override
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {

        return new RecyclerViewDataBinding();
    }
}
