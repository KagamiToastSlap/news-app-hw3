package com.example.rkjc.news_app_2;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointerException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<NewsItem> parseNews(String JSONString) {
        List<NewsItem> arr = new ArrayList<>();
        try
        {
            JSONObject jobject = new JSONObject(JSONString);
            JSONArray articlesArray = jobject.getJSONArray("articles");
            for(int i = 0; i < articlesArray.length(); i++)
            {
                JSONObject articles = articlesArray.getJSONObject(i);
                String author = articles.getString("author");
                String title = articles.getString("title");
                String news_Descriptions = articles.getString("description");
                String theurl = articles.getString("url");
                String urlimage = articles.getString("urlToImage");
                String published = articles.getString("publishedAt");
                arr.add(new NewsItem(author, title, news_Descriptions, theurl, urlimage, published));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return arr;
    }


}


