package com.emmanuel.emmanuelkorircv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.emmanuel.emmanuelkorircv.Utility.SnackBarHelper;
import com.emmanuel.emmanuelkorircv.listeners.PaperOnboardingOnChangeListener;
import com.emmanuel.emmanuelkorircv.listeners.PaperOnboardingOnRightOutListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SwiperAcivity extends AppCompatActivity {
    private int currentApiVersion;
    private FloatingActionButton FloatNext;
    private  Snackbar snack;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
                //persistent hide navbar
                currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final View decorView = getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
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

        setContentView(R.layout.onboarding_main);

        FloatNext =findViewById(R.id.Floating_Button_next);
        final PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnboarding(), getApplicationContext());

        engine.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int oldElementIndex, int newElementIndex) {
                if (newElementIndex==2)
                {
                    snack = Snackbar.make(SwiperAcivity.this.findViewById(android.R.id.content),"Swipe right to continue",      Snackbar.LENGTH_SHORT);
                    SnackBarHelper.configSnackbar(SwiperAcivity.this, snack);
                    snack.show();
                    FloatNext.setVisibility(View.VISIBLE);
                }else
                {
                    snack.dismiss();
                    FloatNext.setVisibility(View.GONE);
                }
             //   Toast.makeText(getApplicationContext(), "Swiped from " + oldElementIndex + " to " + newElementIndex, Toast.LENGTH_SHORT).show();
            }
        });

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                // Probably here will be your exit action
                Intent intent = new Intent(SwiperAcivity.this, MainActivity.class);
                startActivity(intent);
                finish();
               // Toast.makeText(getApplicationContext(), "Swiped out right", Toast.LENGTH_SHORT).show();
            }
        });


        FloatNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SwiperAcivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    // Just example data for Onboarding
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {


        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Hello", "I am a full Stack developer majoring in Software Development with BSC Computer Science",
                Color.parseColor("#c6ffb3"), R.drawable.dragon, R.drawable.ic_key);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("About Me", "I am a software engineer with skills to create and deploy Solutions by creating working softwares and offer software consultany serivices.More can be viewed in my resume Application.",
                Color.parseColor("#b3ffff"), R.drawable.emmanul_me, R.drawable.ic_programmer);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("About App", "This is a sample application to showcase Android Development platform capabilities with some of the latest trends in modern development with a standard UI developed and maintained by me.It also highlights my Resume details.",
                Color.parseColor("#6685EB"), R.drawable.android_robot, R.drawable.ic_android);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}