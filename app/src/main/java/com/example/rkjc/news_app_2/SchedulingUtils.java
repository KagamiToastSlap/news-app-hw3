package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class SchedulingUtils {

    private static final int SHEDULLE_MINUTES = 0;
    private static final int FLEXTIME_SECONDS = 10;

    private static final String NEWS_UPDATE_TAG = "news_update_tag";

    private static boolean running;

    synchronized public static void scheduledSync(@NonNull final Context context){
        if(running)return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintSyncJob = dispatcher.newJobBuilder()
                .setService(NewsJobService.class)
                .setTag(NEWS_UPDATE_TAG)
                .setConstraints(Constraint.ON_UNMETERED_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SHEDULLE_MINUTES,FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        dispatcher.schedule(constraintSyncJob);
        running = true;
    }
}
