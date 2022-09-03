package com.emmanuel.emmanuelkorircv.HelperClasses.ImageHelpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import java.util.NoSuchElementException;

public class ImageScaling {
    public void scaleImage(ImageView view , Context context , int SizeX ,int SizeY) throws NoSuchElementException {
        // Get bitmap from the the ImageView.
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("No drawable on given view");
        } catch (ClassCastException e) {
            // Check bitmap is Ion drawable
            bitmap = Ion.with(view).getBitmap();
        }

        // Get current dimensions AND the desired bounding box
        int width = 0;

        try {
            width = bitmap.getWidth();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
        }

        int height = bitmap.getHeight();
        int boundingx = dpToPx(SizeX, context);
        int boundingy = dpToPx(SizeY, context);
        Log.i("Test", "original width = " + width);
        Log.i("Test", "original height = " + height);
        Log.i("Test", "boundingx = " + boundingx);
        Log.i("Test", "boundingy = " + boundingy);

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) boundingx) / width;
        float yScale = ((float) boundingy) / height;
        float scale = Math.min(xScale, yScale);
        Log.i("Test", "xScale = " + xScale);
        Log.i("Test", "yScale = " + yScale);
        Log.i("Test", "scale = " + scale);

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + width);
        Log.i("Test", "scaled height = " + height);

        // Apply the scaled bitmap
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);

        Log.i("Test", "done");




    }

    private int dpToPx(int dp , Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }



}
