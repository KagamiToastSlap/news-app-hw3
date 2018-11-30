package com.example.rkjc.news_app_2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemDatabase extends RoomDatabase {

    public abstract NewsItemDao newsItemDaoDao();

    private static volatile NewsItemDatabase INSTANCE;

    static NewsItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsItemDatabase.class, "news_item")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
