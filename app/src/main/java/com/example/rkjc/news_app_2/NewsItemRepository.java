package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao newsDao;
    private LiveData<List<NewsItem>> mNewsItem;


    public NewsItemRepository(Application application){
       NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        newsDao = db.newsItemDaoDao();
        mNewsItem = newsDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllNews(){
        return mNewsItem;
    }


    public void syncing(){
        new deleteAsyncTask().execute(newsDao);
        new insertAsyncTask(newsDao).execute();
    }



    private static class insertAsyncTask extends AsyncTask<URL, Void, Void>{
            private NewsItemDao newstaskDao;
            insertAsyncTask(NewsItemDao dao){
                newstaskDao = dao;
            }

        @Override
        protected Void doInBackground(URL... urls) {
                try{
                    JSONObject news = new JSONObject();
                     JsonUtils arts = new JsonUtils();
                     news = new JSONObject(NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL()));
                    List newsarr = new ArrayList();
                    newsarr = arts.parseNews(news.toString());
                     newstaskDao.insert(newsarr);
                }
                 catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<NewsItemDao, Void, Void>{

        @Override
        protected Void doInBackground(NewsItemDao... newsItemDaos) {

            newsItemDaos[0].clearAll();
            return null;
        }
    }



}
