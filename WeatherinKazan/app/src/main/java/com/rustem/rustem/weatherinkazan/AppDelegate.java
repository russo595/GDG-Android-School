package com.rustem.rustem.weatherinkazan;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class AppDelegate extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
