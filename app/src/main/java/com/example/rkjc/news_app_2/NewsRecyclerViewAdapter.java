package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    private final LayoutInflater mInflater;
    private List<NewsItem> mNews;
    private NewsItemViewModel viewModel;

    public NewsRecyclerViewAdapter(Context context, NewsItemViewModel viewModel) {
        this.viewModel = viewModel;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View viewing = mInflater.inflate(R.layout.news_item,parent, false);
        NewsViewHolder newshold = new NewsViewHolder(viewing);
        return newshold;

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position)
    {
        if(mNews != null) {
            NewsItem cNews = mNews.get(position);
            holder.title.setText(cNews.getTitle());
            holder.description.setText(cNews.getPublished() + " . " + cNews.getDescription());

            Uri imageurl = Uri.parse(mNews.get(position).getUrlimage());
            if(imageurl != null){
                Picasso.get().load(imageurl).into(holder.image);
            }

        }
        else{
            holder.title.setText("Title: No Title");
            holder.description.setText("Description: No Description");
            holder.date.setText("Date: No Date");
        }
    }

    void setNews(List<NewsItem> news){
        mNews = news;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if(mNews != null)
            return mNews.size();
        else
            return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView date;
        ImageView image;
        LinearLayout playout;

        public NewsViewHolder(View txt) {
            super(txt);
            title = itemView.findViewById(R.id.Title_txt);
            description = itemView.findViewById(R.id.Description_txt);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            Intent clicked = new Intent(Intent.ACTION_VIEW,Uri.parse(mNews.get(getAdapterPosition()).getUrl()));
            v.getContext().startActivity(clicked);
        }


    }

        public List<NewsItem> getmNews(){
            return mNews;
        }

        public void setmNews(List<NewsItem> mNews){
            this.mNews = mNews;
        }
}

