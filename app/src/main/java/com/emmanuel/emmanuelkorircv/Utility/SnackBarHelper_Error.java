package com.emmanuel.emmanuelkorircv.Utility;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.emmanuel.emmanuelkorircv.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarHelper_Error {
    public static void configSnackbar(Context context, Snackbar snack) {
        addMargins(snack);
        setRoundBordersBg(context, snack);
        setTypeFace(context, snack);
        setToRtl(snack);
        ViewCompat.setElevation(snack.getView(), 6f);
        changeActionTextColor(snack , context);
        animate(snack,context);
    }
    //layout direction
    private static void setToRtl(Snackbar snackbar) {
        ViewCompat.setLayoutDirection(snackbar.getView(),        ViewCompat.LAYOUT_DIRECTION_RTL);
        //center snackbar
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);
        txtv.setPadding(0,0,0,0);



    }
    //text color
    private static void animate(Snackbar snackbar , Context context) {

        //center snackbar
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }else {
            txtv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        txtv.setPadding(0,0,0,0);
    }
    //text color
    private static void changeActionTextColor(Snackbar snackbar , Context context) {
        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorWhite));

    }
    private static void addMargins(Snackbar snack) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snack.getView().getLayoutParams();
        params.setMargins(12, 12, 12, SnackMargin);
        snack.getView().setLayoutParams(params);
    }
    //drawable
    private static void setRoundBordersBg(Context context, Snackbar snackbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            snackbar.getView().setBackground(context.getDrawable(R.drawable.gradient_snack_error));
        }
    }
    private static void setTypeFace(Context context, Snackbar snackbar) {
        TextView tv = (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        TextView snackbarActionTextView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_action );
        tv.setMaxLines(5);
        Typeface font = Typeface.DEFAULT;
        Typeface bold = Typeface.DEFAULT_BOLD;
        tv.setTypeface(font);
        snackbarActionTextView.setTypeface(bold);
    }

    //keyboard helper to make snackbar go above it
    public static boolean isKeyboardHidden = false;
    public static int SnackMargin = 12;
    public static void keyBoardListener(final Activity activity) {
        final View activityRootView = activity.findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(rect);
                int heightRoot = activityRootView.getRootView().getHeight();
                int heightDiff = heightRoot - rect.bottom;
                Log.d("heightRoot", heightRoot + "");
                Log.d("heightDiff", heightDiff + "");
                if (heightDiff > dpToPx(activity, 200)) {
                    if (!isKeyboardHidden) {
                        isKeyboardHidden = true;
                        SnackMargin = heightDiff + 15;
                    }
                } else if (heightDiff < dpToPx(activity, 200)) {
                    if (isKeyboardHidden) {
                        isKeyboardHidden = false;
                        SnackMargin = 12;
                    }
                }
            }
        });
    }

}
