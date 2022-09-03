package com.emmanuel.emmanuelkorircv.HelperClasses.ImageHelpers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.emmanuel.emmanuelkorircv.R;
import com.jsibbold.zoomage.ZoomageView;

import java.util.Objects;

public class ImageViewDialog {

    public void image_dialog(Context context , Bitmap bitmap)
    {
        final Dialog dialog =new Dialog(context,android.R.style.Theme_DeviceDefault_NoActionBar_TranslucentDecor);
        dialog.setContentView(R.layout.dialog_image_view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        dialog.setCancelable(true);
        ZoomageView photoView = dialog.findViewById(R.id.mBigImage);
        ImageView Back = dialog.findViewById(R.id.back_button_dialog);

        Activity activity = (Activity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.black));
        }


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();



        photoView.setImageBitmap(bitmap);
    }


}
