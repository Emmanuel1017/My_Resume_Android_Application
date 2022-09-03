package com.emmanuel.emmanuelkorircv.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences;
import com.kelin.translucentbar.library.TranslucentBarManager;


public class Splash extends AppCompatActivity {

    private int currentApiVersion;
    private SharedPreferences sharedPreferences;
    Animation zoom;
    ImageView img;
    private ConstraintLayout Bgs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences=getSharedPreferences(Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        //persistent hide navbar

        //persistent hide navbar
        //persistent hide navbar
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final View decorView = getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE ;
        decorView.setSystemUiVisibility(uiOptions);


        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        //translucentBarManager.translucent(this);
        translucentBarManager.transparent(this);

        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {
            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    decorView.setSystemUiVisibility(uiOptions);
                }
            }
        });

        //persistent hide navbar end





        setContentView(R.layout.activity_splash);
        zoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        img = findViewById(R.id.image);
        Bgs=findViewById(R.id.background_splash);


        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            img.setImageDrawable(getDrawable(R.drawable.circledark));
            Bgs.setBackgroundColor(Color.parseColor("#000000"));

        }


        img.startAnimation(zoom);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getString(Shared_Preferences.First_Time, Shared_Preferences.First_Time).equals(Shared_Preferences.First_Time))
                {
                    Intent start = new Intent(getApplicationContext(),Start.class);
                    startActivity(start);
                    finish();
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        },4000);

    }


    ///-----------------------------------------------hide status bar ------------------------------------------//
    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    ///-----------------------------------------------hide status bar end------------------------------------------//



}