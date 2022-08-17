package com.emmanuel.emmanuelkorircv.Utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.google.firebase.database.FirebaseDatabase;


public class App extends Application {
    private SharedPreferences sharedPreferences;


    Context context;

    private static final String TAG = "App123";
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        sharedPreferences= getSharedPreferences(com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);


        PRDownloader.initialize(getApplicationContext());

        // Enabling database for resume support even after the application is killed:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
               // .setDatabaseEnabled(true)
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);


        PRDownloader.initialize(context, config);
    }

}
