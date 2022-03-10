package com.emmanuel.emmanuelkorircv;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.emmanuel.emmanuelkorircv.Utility.Utils;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class File_viewer extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout ToolbArLAy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
        }else{
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_file_viewer);
        ToolbArLAy=findViewById(R.id.Layout_custom_toolbar);

        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            ToolbArLAy.setBackgroundResource(R.drawable.gradient_main_dark);
        }else{
            setTheme(R.style.AppTheme);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MY RESUME");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //What to do on back clicked
                // finish() here.
                File_viewer.this.onBackPressed();
                finish();


            }
        });
        PDFView pdfView=findViewById(R.id.pdfView);


        String path = Environment.getExternalStorageDirectory() + "/"
                + Utils.downloadDirectory+ "/" + Utils.downloadFileName;
        File file = new File(path);
        if (file.exists())
        {
            Toast.makeText(File_viewer.this,"This is my resume located at "+path,Toast.LENGTH_LONG).show();
            pdfView.fromFile(file).load();
        }



        else {
            Toast.makeText(File_viewer.this,"Pdf doesn't exist",Toast.LENGTH_SHORT).show();

            pdfView.fromAsset("CV EMMANUEL KORIR (edited).pdf").load();
        }
    }

}