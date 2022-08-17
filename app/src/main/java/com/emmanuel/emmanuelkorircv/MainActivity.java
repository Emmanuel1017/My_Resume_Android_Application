package com.emmanuel.emmanuelkorircv;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.emmanuel.emmanuelkorircv.Utility.MyBounceInterpolator;
import com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hanks.htextview.base.HTextView;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    private ImageView Back ,Me;
    private Menu menu;
    private Map<String ,Object> userData;
    private SharedPreferences sharedPreferences;
    private View TopHeader;
    private CircleImageView Photo;
    private LinearLayout LinLay1,LinLay2,LinLay3,LinLay4,LinLay5,LinLay6;
    private HTextView Name;
    private  Runnable runnableCode ;
    private  PowerMenu powerMenu;
    private ImageView Settings;
    private CardView Contact,Experiences,Personal_data,My_documents,Preferences,Exit;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
        }else{
            setTheme(R.style.AppTheme);
        }


        setContentView(R.layout.activity_main) ;
        userData=new HashMap<>();
        Back= findViewById(R.id.back_button);
        Contact=findViewById(R.id.contact_me);
        Experiences=findViewById(R.id.experiences);
        Personal_data=findViewById(R.id.my_resume);
        My_documents=findViewById(R.id.my_documents);
        Preferences=findViewById(R.id.preferences);
        Exit=findViewById(R.id.exit);
        sharedPreferences=getSharedPreferences(Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);
        TopHeader=findViewById(R.id.bg_top_header);
        LinLay1=findViewById(R.id.Linear_main1);
        LinLay2=findViewById(R.id.Linear_main2);
        LinLay3=findViewById(R.id.Linear_main3);
        LinLay4=findViewById(R.id.Linear_main4);
        LinLay5=findViewById(R.id.Linear_main5);
        LinLay6=findViewById(R.id.Linear_main6);
        Photo=findViewById(R.id.user_photo);





        Settings=findViewById(R.id.settings_icon);
        Name=findViewById(R.id.user_name);


        //NOT NEEDED
        userData.put(Shared_Preferences.First_Time, "not First Time");

        sharedPreferences.edit()
                .putString(Shared_Preferences.First_Time, Shared_Preferences.First_Time+ "not")
                .apply();


        //using two to changE theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            LinearLayout layoutmain = findViewById(R.id.layout_mainativity);
            layoutmain.setBackgroundColor(Color.parseColor("#4E4E4E"));
            TopHeader.setBackgroundResource(R.drawable.ic_bg_topheader_dark);
            LinLay1.setBackgroundResource(R.drawable.gradient_card_dark);
            LinLay2.setBackgroundResource(R.drawable.gradient_card_dark);
            LinLay3.setBackgroundResource(R.drawable.gradient_card_dark);
            LinLay4.setBackgroundResource(R.drawable.gradient_card_dark);
            LinLay5.setBackgroundResource(R.drawable.gradient_card_dark);
            LinLay6.setBackgroundResource(R.drawable.gradient_card_dark);
            Drawable res = getResources().getDrawable(R.drawable.emmanuel_me);

            Photo.setImageDrawable(res);

            Back.setImageDrawable(getDrawable(R.drawable.ic_baseline_arrow_back_white));

            Glide.with(MainActivity.this)
                    .load(R.drawable.setting_dark)
                    .into(Settings);
        }else{
            Drawable resn = getResources().getDrawable(R.drawable.emmanuel_me);
            Photo.setImageDrawable(resn);

            Glide.with(MainActivity.this)
                    .load(R.drawable.preferences)
                    .into(Settings);
        }




        // Create the Handler object (on the main thread by default)
        Handler handler = new Handler();
// Define the code block to be executed
        runnableCode = new Runnable() {
            @Override
            public void run() {
                // checks connection ever 100ms

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("Emmanuel Korir");
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("I am a software engineer");
                    }
                }, 6000);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("I am a android developer");
                    }
                }, 9000);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("I am a web developer");
                    }
                }, 12000);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("I am a ui/ux designer");
                    }
                }, 15000);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Name.animateText("I am a .NET Developer");
                    }
                }, 18000);



                Log.d("Handlers", "Called on Typing Animation");
                // Repeat this the same runnable code block again another 6 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 20000);
            }
        };
// Start the initial runnable task by posting through the handler
        new Thread(() -> {

            try {

                handler.post(runnableCode);
            }catch (Exception e){
                Log.e("Header", e.getMessage());
            }

            // handler.post(runnableCode);

        }).start();


        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Contact.startAnimation(myAnim);

                Intent intent = new Intent(MainActivity.this, Contact.class);
                startActivity(intent);
            }
        });
        Experiences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Experiences.startAnimation(myAnim);

                Intent intent = new Intent(MainActivity.this, Experiences.class);
                startActivity(intent);
            }
        });


        Personal_data.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

            Personal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Personal_data.startAnimation(myAnim);

                Intent intent = new Intent(MainActivity.this, My_data.class);
                startActivity(intent);
            }
        });
        My_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                My_documents.startAnimation(myAnim);

                Intent intent = new Intent(MainActivity.this, Others.class);
                startActivity(intent);
            }
        });
        Preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Preferences.startAnimation(myAnim);

                Intent intent = new Intent(MainActivity.this, Preferences.class);
                startActivity(intent);
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Exit.startAnimation(myAnim);




                //using two to chang theme with this one and the other to change backgrounds for layouts
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){




                    final MaterialAlertDialogBuilder builder  =new MaterialAlertDialogBuilder(MainActivity.this,R.style.AlertDialogThemeDark);
                    builder.setTitle("EXIT");
                    builder.setIcon(R.drawable.ic_exit_2);
                    builder.setCancelable(true);
                    builder.setMessage("Exit, Are you sure?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final Dialog pDialog =new Dialog(MainActivity.this);
                            pDialog.setContentView(R.layout.dialog_exit_layout);
                            Objects.requireNonNull(pDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pDialog.setCancelable(false);
                            pDialog.create();

                            // Sliding animation.
                            if (pDialog.getWindow() != null)
                                pDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

                            pDialog.show();



                            Handler  handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    finishAffinity();
                                    finish();

                                }
                            }, 3000);


                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //still check me out

                        }
                    });
                    builder.create();
                    builder.show();


                } else {


                    final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this, R.style.AlertDialogTheme);
                    builder.setTitle("EXIT");
                    builder.setIcon(R.drawable.ic_exit_2);
                    builder.setCancelable(true);
                    builder.setMessage("Exit, Are you sure?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final Dialog pDialog = new Dialog(MainActivity.this);
                            pDialog.setContentView(R.layout.dialog_exit_layout);
                            Objects.requireNonNull(pDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pDialog.setCancelable(false);
                            pDialog.create();

                            // Sliding animation.
                            if (pDialog.getWindow() != null)
                                pDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

                            pDialog.show();


                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    finishAffinity();
                                    finish();

                                }
                            }, 3000);


                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //still check me out

                        }
                    });
                    builder.create();
                    builder.show();

                }
            }

        });



        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onBackPressed();
            }
        });




        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewx) {
                //powermenu anchor
                powerMenu.showAsDropDown(viewx); // view is an anchor

            }
        });

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            powerMenu = new PowerMenu.Builder(MainActivity.this)
                    // .addItemList(list) //
                    .addItem(new PowerMenuItem("Preferences", R.drawable.setting_dark))
                    .addItem(new PowerMenuItem("Exit", R.drawable.back_dark)) // aad an item list.
                    .setAnimation(MenuAnimation.DROP_DOWN) // Animation start point (TOP | LEFT)
                    .setIconSize(24)
                    .setMenuRadius(10f) // sets the corner radius.
                    .setMenuShadow(10f) // sets the shadow.
                    .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black))
                    .setTextGravity(Gravity.CENTER)
                    .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                    .setTextSize(15)
                    .setBackgroundColor(getColor(R.color.grey))
                    .setSelectedTextColor(R.color.message_bubble_grey)
                    .setMenuColor(Color.WHITE)
                    .setSelected(1)
                    .setDivider(getDrawable(R.drawable.ic_line_h_svgrepo_com_dark))
                    .setDividerHeight(10)
                    .setSelectedMenuColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark))
                    .setSelectedTextColor(Color.WHITE) // sets the color of the selected item text.
                    .setOnMenuItemClickListener(onMenuItemClickListener)
                    .build();
        }else
        {
            powerMenu = new PowerMenu.Builder(MainActivity.this)
                    // .addItemList(list) //
                    .addItem(new PowerMenuItem("Preferences", R.drawable.preferences))
                    .addItem(new PowerMenuItem("Exit", R.drawable.back)) // aad an item list.
                    .setAnimation(MenuAnimation.DROP_DOWN) // Animation start point (TOP | LEFT)
                    .setIconSize(24)
                    .setMenuRadius(10f) // sets the corner radius.
                    .setMenuShadow(10f) // sets the shadow.
                    .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.purple_700))
                    .setTextGravity(Gravity.CENTER)
                    .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                    .setTextSize(15)
                    .setSelectedTextColor(Color.WHITE)
                    .setMenuColor(Color.WHITE)
                    .setSelected(1)
                    .setDivider(getDrawable(R.drawable.ic_line_h_svgrepo_com))
                    .setDividerHeight(10)
                    .setSelectedMenuColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark))
                    .setSelectedTextColor(Color.WHITE) // sets the color of the selected item text.
                    .setOnMenuItemClickListener(onMenuItemClickListener)
                    .build();
        }

    }


    //poewer menu item
    private final OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {
            // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            powerMenu.setSelectedPosition(position); // change selected item
            if (item.getTitle().equals("Exit")){
                final MaterialAlertDialogBuilder builderz  =new MaterialAlertDialogBuilder(MainActivity.this,R.style.AlertDialogTheme);
                builderz.setTitle("Exit");
                builderz.setIcon(R.drawable.ic_exit_2);
                builderz.setMessage("Confirm Exit");
                builderz.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finishAffinity();

                    }
                });
                builderz.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builderz.show();



            } else if (item.getTitle().equals("Preferences")){


                Intent intent = new Intent(MainActivity.this, Preferences.class);
                startActivity(intent);


            }


            powerMenu.dismiss();
        }
    };




}