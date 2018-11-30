package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "news_item")
public class NewsItem {

  @NonNull
  @PrimaryKey(autoGenerate = true)
  private int id;


  @NonNull
  @ColumnInfo(name = "aurthor")
  private String aurthor;

  @NonNull
  @ColumnInfo(name = "title")
  private String title;

  @NonNull
  @ColumnInfo(name = "Description")
  private String Description;

  @NonNull
  @ColumnInfo(name = "theurl")
  private String url;

  @NonNull
  @ColumnInfo(name = "urlimage")
  private String urlimage;

  @NonNull
  @ColumnInfo(name = "published")
  private String published;


  public NewsItem (String aurthor, String title, String Description, String url, String urlimage, String published, int id)
  {
    this.aurthor = aurthor;
    this.title = title;
    this.Description = Description;
    this.url = url;
    this.urlimage = urlimage;
    this.published = published;
    this.id = id;
  }

  @Ignore
  public NewsItem (String aurthor, String title, String Description, String url, String urlimage, String published)
  {
    this.aurthor = aurthor;
    this.title = title;
    this.Description = Description;
    this.url = url;
    this.urlimage = urlimage;
    this.published = published;
  }

  public String getAurthor()
  {
    return aurthor;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return Description;
  }

  public String getUrl()
  {
    return url;
  }

  public String getUrlimage()
  {
    return urlimage;
  }

  public String getPublished()
  {
    return published;
  }

  public int getId(){
    return id;
  }


}