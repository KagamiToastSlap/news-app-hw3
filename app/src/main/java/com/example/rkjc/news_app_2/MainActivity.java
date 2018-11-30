package com.example.rkjc.news_app_2;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recviews;
    private NewsItemViewModel newsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recviews = findViewById(R.id.news_recyclerview);
        newsView = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        final NewsRecyclerViewAdapter adapt = new NewsRecyclerViewAdapter(this,newsView);
        recviews.setAdapter(adapt);
        recviews.setLayoutManager(new LinearLayoutManager(this));
        newsView.getAllNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                adapt.setNews(newsItems);
            }
        });
        SchedulingUtils.scheduledSync(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem news) {
        int itemThatWasClickedId = news.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            newsView.syncNews();
            return true;
        }
        return super.onOptionsItemSelected(news);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_news, menu);
        return true;
    }


}
