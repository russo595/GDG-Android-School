package com.gdgkazan.punkapi;

import android.app.Application;

import com.gdgkazan.punkapi.api.ApiFactory;
import com.facebook.stetho.Stetho;

public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiFactory.provideClient();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
