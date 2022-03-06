package com.emmanuel.emmanuelkorircv;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class Others extends AppCompatActivity {
private ImageView Others_back;
private ConstraintLayout BgOthers;
private LottieAnimationView Lottie;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
        }else{
            setTheme(R.style.AppTheme);
        }


        setContentView(R.layout.activity_others);

        Others_back=findViewById(R.id.back_button_others);
        BgOthers=findViewById(R.id.background_others);
        Lottie=findViewById(R.id.animationViewOthers);


        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            BgOthers.setBackgroundColor(Color.parseColor("#4E4E4E"));
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Lottie.setAnimation(R.raw.construction);
                Lottie.playAnimation();
                Lottie.setRepeatCount(LottieDrawable.INFINITE);// for Infinite loops


            }
        }, 2500);



        Others_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Others.this.onBackPressed();
            }
        });
    }
}