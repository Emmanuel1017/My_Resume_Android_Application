package com.emmanuel.emmanuelkorircv;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import pl.hypeapp.materialtimelineview.MaterialTimelineView;

public class Experiences extends AppCompatActivity {

    private ImageView Back ,Me;
    private TextView Exp1,Exp2,Exp3;
    private LinearLayout Card1,Card2;
    private ConstraintLayout Background;
    private MaterialTimelineView View1,View2,View3;
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

        setContentView(R.layout.activity_experiences);

        Back=findViewById(R.id.back_button_experiences);
        Card1=findViewById(R.id.Experiences_Card1);
        Card2=findViewById(R.id.Experiences_Card2);
        Background=findViewById(R.id.Main_Layout_Experiences);
        View1=findViewById(R.id.material_timeline_view);
        View2=findViewById(R.id.material_timeline_view5);
        View3=findViewById(R.id.material_timeline_viewmiddle5);
        Exp1=findViewById(R.id.Experiences_Textview1);
        Exp2=findViewById(R.id.Experiences_Textview2);
        Exp3=findViewById(R.id.Experiences_Textview3);




        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            Card1.setBackgroundResource(R.drawable.gradient_card_dark);
            Card2.setBackgroundResource(R.drawable.gradient_card_dark);
            Background.setBackgroundColor(Color.parseColor("#4E4E4E"));
            View1.setBackgroundColor(Color.parseColor("#4E4E4E"));
            View2.setBackgroundColor(Color.parseColor("#4E4E4E"));
            View3.setBackgroundColor(Color.parseColor("#4E4E4E"));
            Exp1.setTextColor(getColor(R.color.white));
            Exp2.setTextColor(getColor(R.color.white));
            Exp3.setTextColor(getColor(R.color.white));
            View1.setBottomRadioColor(getColor(R.color.white));
            View2.setTopRadioColor(getColor(R.color.white));
            View3.setTopRadioColor(getColor(R.color.white));
            View3.setBottomRadioColor(getColor(R.color.white));
            View3.setLineColor(getColor(R.color.white));
            Back.setImageDrawable(getDrawable(R.drawable.ic_baseline_arrow_back_white));
        }

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Experiences.this.onBackPressed();
            }
        });
    }
}