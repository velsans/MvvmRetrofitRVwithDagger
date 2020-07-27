package com.vel.mvvmretrofitrvwithdagger.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vel.mvvmretrofitrvwithdagger.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        //ActionbarTitle("About Canada");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsFragment.newInstance())
                    .commitNow();
        }
    }

    public void ActionbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Add is Clicked", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.reset:
                Toast.makeText(this, "Nothing is selected", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.about:
                Toast.makeText(this, "About is Clicked", Toast.LENGTH_LONG).show();
                return (true);
            case R.id.exit:
                //finish();
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
}