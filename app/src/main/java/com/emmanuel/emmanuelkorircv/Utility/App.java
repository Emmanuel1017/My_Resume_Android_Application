package com.emmanuel.emmanuelkorircv.Utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.emmanuel.emmanuelkorircv.R;
import com.google.firebase.database.FirebaseDatabase;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


public class App extends Application {
    private SharedPreferences sharedPreferences;


    private static Context context;
    private  File dir_;


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


        //init caching directory
        dir_ = new File(context.getFilesDir(), AppConstants.Image_Caching_Directory);
        if(!dir_.exists()) {
            dir_.mkdirs();
        }
        //image caching init

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user)
                .showImageForEmptyUri(R.drawable.user)
                .showImageOnFail(R.drawable.user)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();


        File cacheDir = StorageUtils.getOwnCacheDirectory(context, dir_.getPath(), false);

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(1080, 1800) // default = device screen dimensions
                .diskCacheExtraOptions(1080, 1800, null)
                .threadPoolSize(3) // default
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                //.memoryCache(new LruMemoryCache(500 * 1024 * 1024))
                .memoryCacheSizePercentage(70) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }




    public static Context getContext() {
        return context;
    }

    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false

    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused

    }

}
