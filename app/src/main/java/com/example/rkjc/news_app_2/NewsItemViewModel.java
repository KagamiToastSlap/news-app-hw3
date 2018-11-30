package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository newsItemRepository;

    private LiveData<List<NewsItem>> allNews;

    public NewsItemViewModel (Application application) {
        super(application);
        newsItemRepository = new NewsItemRepository(application);
        allNews = newsItemRepository.getAllNews();
    }

    public LiveData<List<NewsItem>> getAllNews() {
        return allNews;
    }


    public void syncNews(){
        newsItemRepository.syncing();

    }
}
