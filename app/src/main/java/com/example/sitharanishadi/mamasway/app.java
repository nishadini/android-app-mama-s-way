package com.example.sitharanishadi.mamasway;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(10));
    }
}
