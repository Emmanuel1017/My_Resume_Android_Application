package com.emmanuel.emmanuelkorircv;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.emmanuel.emmanuelkorircv.Utility.MyBounceInterpolator;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.scwang.wave.MultiWaveHeader;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Contact extends AppCompatActivity {

    private ImageButton linkedin, github, twitter,whtsapp;
    private String llinkedin, lgit, lmail, lrepo;
    private Animation animblink, animbounce;
    private Intent intent,intent2;
    private Toolbar toolbar;
    private final int CODE =1;
    private ImageView Call,Back;
    private MultiWaveHeader WaveHeader;
    private ImageView tv_star;
    private ConstraintLayout Backbg;
    private TextView Contactme,Compuresci,Contactme2;
    private View View1,View2,View3;
    private LinearLayout CantactLinear;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("UseCompatLoadingForDrawables")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
        }else{
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_contact);

        init();
        llinkedin = "https://www.linkedin.com/in/emmanuel-korir-0a06b1ba/";
        lgit = "https://github.com/Emmanuel1017";
        lmail = "koriremmanuel@rocketmail.com";
        lrepo = "https://www.linkedin.com/in/emmanuel-korir-0a06b1ba/";

        animblink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        animbounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake_long);

        tv_star.setVisibility(View.VISIBLE);
        tv_star.startAnimation(animbounce);
        Backbg=findViewById(R.id.Background_contacts);

        WaveHeader=findViewById(R.id.Multiwave_Header);
        Contactme=findViewById(R.id.Contact_Me_Text);
        Compuresci=findViewById(R.id.computer_science);
        Contactme2=findViewById(R.id.Contacts_textview);
        Contactme=findViewById(R.id.Contact_Me_Text);
        Compuresci=findViewById(R.id.computer_science);
        View1=findViewById(R.id.view_contact_1);
        View2=findViewById(R.id.view_contact_2);
        View3=findViewById(R.id.view3);
        CantactLinear=findViewById(R.id.Linear_Layout_contact);




        //using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            WaveHeader.setCloseColor(getColor(R.color.colorTextDisabled));
            WaveHeader.setStartColor(getColor(R.color.black));
            linkedin.setImageDrawable(getDrawable(R.drawable.ic_linkedin_dark));
            github.setImageDrawable(getDrawable(R.drawable.ic_github_dark));
            whtsapp.setImageDrawable(getDrawable(R.drawable.ic_whatsapp_dark));
            twitter.setImageDrawable(getDrawable(R.drawable.ic_baseline_email_dark));
            Call.setImageDrawable(getDrawable(R.drawable.ic_baseline_local_phone_dark));
            tv_star.setImageDrawable(getDrawable(R.drawable.ic_internet_dark));
            Backbg.setBackgroundColor(Color.parseColor("#4E4E4E"));
            Contactme2.setTextColor(getColor(R.color.white));
            Contactme.setTextColor(getColor(R.color.white));
            Compuresci.setTextColor(getColor(R.color.white));
            View1.setBackgroundColor(getColor(R.color.white));
            View2.setBackgroundColor(getColor(R.color.white));
            View3.setBackgroundColor(getColor(R.color.white));
            CantactLinear.setBackgroundColor(getColor(R.color.grey));

            Back.setImageDrawable(getDrawable(R.drawable.ic_baseline_arrow_back_white));
        }


        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                linkedin.startAnimation(myAnim);
                //animation lottie
                final Dialog lDialog =new Dialog(Contact.this);
                lDialog.setContentView(R.layout.dialog_linkedin_layout);
                Objects.requireNonNull(lDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                lDialog.setCancelable(true);
                lDialog.create();
                lDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(llinkedin));
                startActivity(i);
                lDialog.dismiss();

                    }
                }, 2000);

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Contact.this.onBackPressed();
            }
        });


        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                github.startAnimation(myAnim);


                //animation lottie
                final Dialog gDialog =new Dialog(Contact.this);
                gDialog.setContentView(R.layout.dialog_github_layout);
                Objects.requireNonNull(gDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                gDialog.setCancelable(true);
                gDialog.create();
                gDialog.show();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        gDialog.dismiss();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(lgit));
                        startActivity(i);



                    }
                }, 5000);



            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                twitter.startAnimation(myAnim);

                //animation lottie
                final Dialog aDialog =new Dialog(Contact.this);
                aDialog.setContentView(R.layout.dialog_email_layout);
                Objects.requireNonNull(aDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                aDialog.setCancelable(true);
                aDialog.create();
                aDialog.show();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        String[] TO = {"koriremmanuel@rocketmail.com"};
                        String[] CC = {""};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        aDialog.dismiss();

                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_CC, CC);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regard from your CV");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {

                            final SweetAlertDialog pDialog = new SweetAlertDialog(Contact.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setCustomImage(R.drawable.ic_baseline_email);
                            pDialog.setTitleText("No email application found");
                            pDialog.setCancelable(false);
                            pDialog.show();


                        }


                    }
                }, 2000);


            }
        });

        ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container_name);
        container.startShimmer(); // If auto-start is set to false

        ShimmerFrameLayout container1 = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_containerpic);
        container1.startShimmer(); // If auto-start is set to false





        whtsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                whtsapp.startAnimation(myAnim);

                //animation lottie
                final Dialog sDialog =new Dialog(Contact.this);
                sDialog.setContentView(R.layout.dialog_whatsapp_layout);
                Objects.requireNonNull(sDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                sDialog.setCancelable(true);
                sDialog.create();
                sDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        sDialog.dismiss();
                        String url = "https://api.whatsapp.com/send?phone=254745317920";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                }, 4000);
            }
        });

        tv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                tv_star.startAnimation(myAnim);

                tv_star.startAnimation(animblink);
                //animation lottie
                final Dialog sDialog =new Dialog(Contact.this);
                sDialog.setContentView(R.layout.dialog_website_layout);
                Objects.requireNonNull(sDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                sDialog.setCancelable(true);
                sDialog.create();
                sDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        sDialog.dismiss();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cariboudevs.com/"));
                startActivity(browserIntent);

                    }
                }, 4000);
            }
        });


        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Contact.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.04, 30);
                myAnim.setInterpolator(interpolator);
                Call.startAnimation(myAnim);


                //animation lottie
                final Dialog cDialog =new Dialog(Contact.this);
                cDialog.setContentView(R.layout.dialog_call_layout);
                Objects.requireNonNull(cDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cDialog.setCancelable(true);
                cDialog.create();
                cDialog.show();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        cDialog.dismiss();

                final MaterialAlertDialogBuilder builder  =new MaterialAlertDialogBuilder(Contact.this,R.style.AlertDialogTheme);
                builder.setTitle("Call confirmation");
                builder.setIcon(R.drawable.phone_call);
                builder.setCancelable(true);
                builder.setMessage("Call Me on 0745317920?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        // TODO Auto-generated method stub

                        intent2 = new Intent(Intent.ACTION_CALL);
                        intent2.setData(Uri.parse("tel:"+"+254745317920"));
                        checkPermission(Manifest.permission.CALL_PHONE,
                                CODE);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //you can still check me out

                    }
                });
                builder.create();
                builder.show();

                    }
                }, 2000);


            }
        });

    }




    private void starRepo() {
        final MaterialAlertDialogBuilder builder  =new MaterialAlertDialogBuilder(Contact.this,R.style.AlertDialogTheme);
        builder.setTitle("Call Me?");
        builder.setIcon(R.drawable.github_profile);
        builder.setCancelable(true);
        builder.setMessage("This action will call 0745317920. Do you want to call dev ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tv_star.startAnimation(animbounce);
                // TODO Auto-generated method stub

                intent2 = new Intent(Intent.ACTION_CALL);
                intent2.setData(Uri.parse("tel:"+"+254745317920"));
                checkPermission(Manifest.permission.CALL_PHONE,
                        CODE);



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


    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {
        ///// Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(Contact.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Contact.this, new String[] { permission }, requestCode);
        }
        else{
            startActivity(intent2);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);

        if (requestCode == CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Contact.this, "Call Permission Granted", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
            }
            else {
                Toast.makeText(Contact.this, "Call Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private  void init(){
        linkedin=findViewById(R.id.linkedin);
        github=findViewById(R.id.github);
        twitter=findViewById(R.id.twitter);
        tv_star=findViewById(R.id.star);
        Call=findViewById(R.id.Callimg);
        Back=findViewById(R.id.back_button_contact);
        whtsapp=findViewById(R.id.whatsapp);
    }




}