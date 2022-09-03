package com.emmanuel.emmanuelkorircv.HelperClasses.ImageHelpers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.emmanuel.emmanuelkorircv.HelperClasses.StatusBarColor;
import com.emmanuel.emmanuelkorircv.R;
import com.jsibbold.zoomage.ZoomageView;
import com.skydoves.transformationlayout.TransformationAppCompatActivity;
import com.skydoves.transformationlayout.TransformationCompat;

public class PhotoViewer extends TransformationAppCompatActivity {

    private ZoomageView photoView;
    private ImageView Back;
    private String uri;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransformationCompat.onTransformationStartContainer(PhotoViewer.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        photoView = findViewById(R.id.mBigImage);
        Back = findViewById(R.id.back_button_dialog);
        intent = getIntent();
        uri = intent.getStringExtra("uri");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewer.this.onBackPressed();
            }
        });

        new StatusBarColor().StatusbarColor(PhotoViewer.this,R.color.grey);

        new ImageGlideHelper().load_And_Cache_image(PhotoViewer.this , photoView , uri );
    }
}