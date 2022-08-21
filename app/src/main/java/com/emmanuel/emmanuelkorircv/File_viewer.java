package com.emmanuel.emmanuelkorircv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.emmanuel.emmanuelkorircv.Utility.Snackbar.SnackBarHelper;
import com.emmanuel.emmanuelkorircv.Utility.Utils;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class File_viewer extends AppCompatActivity {
    private LinearLayout ToolbArLAy;
    private File dir_;

    private String Download_File_Name = "";
    private ImageView Back;

    private final Activity activity = File_viewer.this;
    private final Context context = File_viewer.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppTheme_NoActionBarTransparent);
        }else{
            setTheme(R.style.AppTheme_NoActionBarTransparent);
        }

        setContentView(R.layout.activity_file_viewer);





        PDFView pdfView=findViewById(R.id.pdfView);
        Back = findViewById(R.id.back_pdf_viewer);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File_viewer.this.onBackPressed();
                finish();
            }
        });

        dir_ = new File(getApplicationContext().getFilesDir(), Utils.downloadDirectory);
        if(!dir_.exists()) {
            dir_.mkdirs();
        }

        //get intent
        Download_File_Name = getIntent().getExtras().getString("File_Name","");


        String path = dir_.getPath() + File.separator + Download_File_Name;
        File file = new File(path);

        if (file.exists())
        {
            Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"This is my Latest Updated CV",      Snackbar.LENGTH_SHORT);
            SnackBarHelper.configSnackbar(context, snack);
            snack.show();
            pdfView.fromFile(file).load();
        }
        else
        {
            Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"This is a old Resume, Activate data to view my latest resume",      Snackbar.LENGTH_SHORT);
            SnackBarHelper.configSnackbar(context, snack);
            snack.show();
            pdfView.fromAsset("CV EMMANUEL KORIR (edited).pdf").load();
        }
    }

}