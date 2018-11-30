package com.example.rkjc.news_app_2;

import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class NewsJobService extends JobService {



    @Override
    public boolean onStartJob(final JobParameters job) {
        new NewsItemRepository(getApplication()).syncing();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
