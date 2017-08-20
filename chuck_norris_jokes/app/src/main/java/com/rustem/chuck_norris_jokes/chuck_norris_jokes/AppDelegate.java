package com.rustem.chuck_norris_jokes.chuck_norris_jokes;

import android.app.Application;

import com.example.rustem.chuck_norris_jokes.BuildConfig;
import com.facebook.stetho.Stetho;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.service.ApiFactory;

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
