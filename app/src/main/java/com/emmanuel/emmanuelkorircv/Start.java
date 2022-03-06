package com.emmanuel.emmanuelkorircv;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.emmanuel.emmanuelkorircv.Utility.MyBounceInterpolator;

public class Start extends AppCompatActivity {


    private ConstraintLayout constraintLayout;
    private Button Continue;
    private int currentApiVersion;
    private Animation animblink,animSlideup,Fadeup;
    private ImageView Back;private TextView Letsgo,Letsgodesc;
    private FragmentManager fragmentManager;
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
        setContentView(R.layout.activity_start);



        fragmentManager = getSupportFragmentManager();
        animblink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink_short);

        Fadeup = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeup);

        animSlideup = AnimationUtils.loadAnimation(Start.this,
                R.anim.slidingup);

        Continue=findViewById(R.id.get_access_btn);
        Back=findViewById(R.id.back_Start);

        Letsgo=findViewById(R.id.lets_go_tv);
        Letsgodesc=findViewById(R.id.lets_go_description_tv);
        setupWindowAnimations();

        //animation gradient Background
        ConstraintLayout constraintLayout = findViewById(R.id.Animated_constrain_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        //end of animation

          Events_Start();




        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Animation myAnim = AnimationUtils.loadAnimation(Start.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Continue.startAnimation(myAnim);


                Intent intent = new Intent(Start.this, SwiperAcivity.class);
                startActivity(intent);
                finish();
            }
        });




        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start.this.onBackPressed();
            }
        });

    }


    private  void  Events_Start()
    {

        // Create the Handler object (on the main thread by default)
        Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // checks connection ever 100ms

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Continue.setVisibility(View.INVISIBLE);
                        Letsgo.setText("MY RESUME");
                    }
                }, 1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Letsgo.setText("More Than A Resume");
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Letsgodesc.setVisibility(View.VISIBLE);
                        Letsgodesc.startAnimation(Fadeup);
                        }
                }, 2500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Continue.setVisibility(View.VISIBLE);
                        //Continue.startAnimation(Fadeup);
                        Continue.startAnimation(animSlideup);

                    }
                }, 3500);


                Log.d("Handlers", "Called on Start");
                // Repeat this the same runnable code block again another 6 seconds
                // 'this' is referencing the Runnable object

            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);




    }


    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }
}