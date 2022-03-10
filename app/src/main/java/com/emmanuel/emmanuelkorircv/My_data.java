package com.emmanuel.emmanuelkorircv;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.emmanuel.emmanuelkorircv.Utility.CheckForSDCard;
import com.emmanuel.emmanuelkorircv.Utility.DownloadTask;
import com.emmanuel.emmanuelkorircv.Utility.SnackBarHelper;
import com.emmanuel.emmanuelkorircv.Utility.Utils;
import com.emmanuel.emmanuelkorircv.databinding.ActivityMyDataBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.hanks.htextview.base.HTextView;

import java.io.File;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class My_data extends AppCompatActivity {

    private ImageView Call,Profilepic,Backdata;
     FloatingActionButton Faba;
    private Button button;
    private TextView MyName;
    private DownloadManager manager;
    private ActivityMyDataBinding binding;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private HTextView LineT;
    ArrayList<String> Arraymsgs = new ArrayList<>();
    ArrayList<Integer> Arrayclrs = new ArrayList<Integer>();
    int Position= 0;
    private TextView MyInfo,MySkills,ProgrammingSkills;
    private View View1,View2;
    private CoordinatorLayout MyView;
    private CollapsingToolbarLayout CollapseToolbar;
    private LinearLayout Linear1,Linear2,Linear3,Linear4,Linear5,Linear6,Linear7,Linear8,Linear9,Linear10,Linear11,Linear12,Linear13,Linear14,Linear15,Linear16,Linear17,Linear18,Linear19,Linear20,Linear21,Linear22,Linear23,Linear24,Linear25,Linear26,Linear27,Linear28;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyDataBinding.inflate(getLayoutInflater());


//using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppTheme_NoActionBarTransparentDark);

        }else{
            setTheme(R.style.AppTheme_NoActionBarTransparent);
        }

        setContentView(binding.getRoot());

        MyName=findViewById(R.id.my_name);
        LineT=findViewById(R.id.Line_Htext_View);
        Profilepic=findViewById(R.id.My_pic_Toolbar);
        MyInfo=findViewById(R.id.My_information_textview);
        MySkills=findViewById(R.id.text_view_myskills);
        ProgrammingSkills=findViewById(R.id.programming_skills);
        View1=findViewById(R.id.view2_mydta);
        View2=findViewById(R.id.view3_mydata);
        MyView=findViewById(R.id.main_layout_mydata);
        CollapseToolbar=findViewById(R.id.toolbar_layout);
        Backdata=findViewById(R.id.back_button_my_data);


        Linear1=findViewById(R.id.Linear_View_1);
        Linear2=findViewById(R.id.Linear_View_2);
        Linear3=findViewById(R.id.Linear_View_3);
        Linear4=findViewById(R.id.Linear_View_4);
        Linear5=findViewById(R.id.Linear_View_5);
        Linear6=findViewById(R.id.Linear_View_6);
        Linear7=findViewById(R.id.Linear_View_7);
        Linear8=findViewById(R.id.Linear_View_8);
        Linear9=findViewById(R.id.Linear_View_9);
        Linear10=findViewById(R.id.Linear_View_10);
        Linear11=findViewById(R.id.Linear_View_11);
        Linear12=findViewById(R.id.Linear_View_12);
        Linear13=findViewById(R.id.Linear_View_13);
        Linear14=findViewById(R.id.Linear_View_14);
        Linear15=findViewById(R.id.Linear_View_15);
        Linear16=findViewById(R.id.Linear_View_16);
        Linear17=findViewById(R.id.Linear_View_17);
        Linear18=findViewById(R.id.Linear_View_18);
        Linear19=findViewById(R.id.Linear_View_19);
        Linear20=findViewById(R.id.Linear_View_20);
        Linear21=findViewById(R.id.Linear_View_21);
        Linear22=findViewById(R.id.Linear_View_22);
        Linear23=findViewById(R.id.Linear_View_23);
        Linear24=findViewById(R.id.Linear_View_24);
        Linear25=findViewById(R.id.Linear_View_25);
        Linear26=findViewById(R.id.Linear_View_26);
        Linear27=findViewById(R.id.Linear_View_27);
        Linear28=findViewById(R.id.Linear_View_28);


        //line textview array
        Arraymsgs.add("Python");
        Arraymsgs.add("java");
        Arraymsgs.add("C");
        Arraymsgs.add("C#");
        Arraymsgs.add("C++");
        Arraymsgs.add("ANGULAR");
        Arraymsgs.add("PHP");
        Arraymsgs.add("HTML/CSS");
        Arraymsgs.add("JAVASCRIPT");
        Arraymsgs.add("VUE");
        Arraymsgs.add("REACT");
        Arraymsgs.add("SHELL");
        Arraymsgs.add("VB");
        Arraymsgs.add("KOTLIN");
        Arraymsgs.add("DART");
        Arraymsgs.add("RUBY");
        Arraymsgs.add("Unity");
        Arraymsgs.add("React-native");
        Arraymsgs.add("OpenCv/MTL");
        Arraymsgs.add("CISCO");
        Arraymsgs.add("SSL");
        Arraymsgs.add("Daraja/Mpesa Api");


        //line textview array colors
        Arrayclrs.add(getColor(android.R.color.holo_purple));
        Arrayclrs.add(getColor(R.color.colorGreen));
        Arrayclrs.add(getColor(R.color.colorRed_01));
        Arrayclrs.add(getColor(R.color.seven));
        Arrayclrs.add(getColor(R.color.third));
        Arrayclrs.add(getColor(R.color.blueDark));
        Arrayclrs.add(getColor(R.color.teal_200));
        Arrayclrs.add(getColor(R.color.light_blue_900));
        Arrayclrs.add(getColor(R.color.pink));
        Arrayclrs.add(getColor(R.color.teal_200));
        Arrayclrs.add(getColor(R.color.third1));
        Arrayclrs.add(getColor(R.color.black));
        Arrayclrs.add(getColor(R.color.seven));
        Arrayclrs.add(getColor(android.R.color.holo_red_dark));
        Arrayclrs.add(getColor(R.color.light_blue_900));
        Arrayclrs.add(getColor(R.color.colorRed_01));
        Arrayclrs.add(getColor(R.color.purple_500));
        Arrayclrs.add(getColor(R.color.design_default_color_secondary));
        Arrayclrs.add(getColor(R.color.third));
        Arrayclrs.add(getColor(R.color.green_start));
        Arrayclrs.add(getColor(R.color.black));
        Arrayclrs.add(getColor(R.color.blue));







        Faba =findViewById(R.id.fab);


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);






        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle("       My Resume");



        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            MySkills.setTextColor(Color.parseColor("#FFFFFF"));
            MyInfo.setTextColor(Color.parseColor("#FFFFFF"));
            ProgrammingSkills.setTextColor(Color.parseColor("#FFFFFF"));
            View1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            MyView.setBackgroundColor(Color.parseColor("#4E4E4E"));
            Faba.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.black)));
            Faba.setImageDrawable(getDrawable(R.drawable.ic_pdf_file_dark));
            CollapseToolbar.setBackground(getDrawable(R.drawable.dark_sky));
            toolbar.setPopupTheme(R.style.Theme_EmmanuelKorirCV_NoActionBar);




        }





        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            Typing_animationDark();

            //animate  line text view using array
            LineT.animateText(Arraymsgs.get(Position));


            Handler handler =new Handler();
            //delay linetext view change text from array
            Runnable runnableCode = new Runnable() {
                @Override
                public void run() {

                    if(Position>=Arraymsgs.size())
                        Position = 0;
                    LineT.animateText(Arraymsgs.get(Position));
                    Position++;
                    handler.postDelayed(this, 1800);
                }
            };
// Start the initial runnable task by posting through the handler
            handler.post(runnableCode);

            Linear1.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear2.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear3.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear4.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear5.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear6.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear7.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear8.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear9.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear10.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear11.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear12.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear13.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear14.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear15.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear16.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear17.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear18.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear19.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear20.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear21.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear22.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear23.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear24.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear25.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear26.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear27.setBackgroundResource(R.drawable.gradient_card_dark);
            Linear28.setBackgroundResource(R.drawable.gradient_card_dark);

        }else{

            //animate  line text view using array
            LineT.animateText(Arraymsgs.get(Position));


            Handler handler1 =new Handler();
            //delay linetext view change text from array
            Runnable runnableCode1 = new Runnable() {
                @Override
                public void run() {

                    if(Position>=Arraymsgs.size())
                        Position = 0;
                    LineT.animateText(Arraymsgs.get(Position));
                    LineT.setTextColor(Arrayclrs.get(Position));
                    Position++;



                    handler1.postDelayed(this, 1800);
                }
            };
// Start the initial runnable task by posting through the handler
            handler1.post(runnableCode1);

            Typing_animation();
        }



        FloatingActionButton fab = binding.fab;
        Faba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

            }
        });






        MyName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


            }
        });


        Backdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //What to do on back clicked
                // finish() here.
                My_data.this.onBackPressed();
                finish();



            }
        });






    }
    public void GradientText()
    {
        //gradient text view


        TextPaint paint = MyName.getPaint();
        float width = paint.measureText(MyName.getText().toString());

        final Shader textShader = new LinearGradient(0, 0, width, MyName.getTextSize(),
                new int[]{
                        Color.parseColor("#02147A"),
                        Color.parseColor("#00B8D4"),


                }, null, Shader.TileMode.CLAMP);
        MyName.getPaint().setShader(textShader);

        //end of color



    }
    public void GradientTextDark()
    {
        //gradient text view


        TextPaint paint = MyName.getPaint();
        float width = paint.measureText(MyName.getText().toString());

        final Shader textShader = new LinearGradient(0, 0, width, MyName.getTextSize(),
                new int[]{

                        Color.parseColor("#000000"),
                        Color.parseColor("#A6A6A6"),


                }, null, Shader.TileMode.CLAMP);
        MyName.getPaint().setShader(textShader);

        //end of color



    }


    // Typing function
    public void Typing_animation()
    {
        // Create the Handler object (on the main thread by default)
        Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // checks connection ever 100ms

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.setText("E");
                GradientText();
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("M");
                GradientText();
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("M");
                GradientText();
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("A");
                GradientText();
            }
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("N");
                GradientText();
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("U");
                GradientText();
            }
        }, 1800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("E");
                GradientText();
            }
        }, 2100);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyName.append("L");
                GradientText();
            }

        }, 2400);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("_");
                        GradientText();
                    }

                }, 2600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("K");
                        GradientText();
                    }

                }, 2800);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("O");
                        GradientText();
                    }

                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("R");
                        GradientText();
                    }

                }, 3200);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("I");
                        GradientText();
                    }

                }, 3400);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("R");
                        GradientText();
                    }

                }, 3600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Removing , from the end if any

                           String Namenew = "EMMANUEL_KORIR".replaceAll("_KORIR","");
                            MyName.setText(Namenew);
                        GradientText();
                    }

                }, 3800);


                Log.d("Handlers", "Called on Typing Animation");
                // Repeat this the same runnable code block again another 6 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 6000);
            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);

    }

    // Typing function
    public void Typing_animationDark()
    {
        // Create the Handler object (on the main thread by default)
        Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // checks connection ever 100ms

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.setText("E");
                        GradientTextDark();
                    }
                }, 300);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("M");
                        GradientTextDark();
                    }
                }, 600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("M");
                        GradientTextDark();
                    }
                }, 900);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("A");
                        GradientTextDark();
                    }
                }, 1200);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("N");
                        GradientTextDark();
                    }
                }, 1500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("U");
                        GradientTextDark();
                    }
                }, 1800);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("E");
                        GradientTextDark();
                    }
                }, 2100);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("L");
                        GradientTextDark();
                    }

                }, 2400);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("_");
                        GradientTextDark();
                    }

                }, 2600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("K");
                        GradientTextDark();
                    }

                }, 2800);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("O");
                        GradientTextDark();
                    }

                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("R");
                        GradientTextDark();
                    }

                }, 3200);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("I");
                        GradientTextDark();
                    }

                }, 3400);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MyName.append("R");
                        GradientTextDark();
                    }

                }, 3600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Removing , from the end if any

                        String Namenew = "EMMANUEL_KORIR".replaceAll("_KORIR","");
                        MyName.setText(Namenew);
                        GradientTextDark();
                    }

                }, 3800);


                Log.d("Handlers", "Called on Typing Animation");
                // Repeat this the same runnable code block again another 6 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 6000);
            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);

    }



    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(My_data.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(My_data.this, new String[] { permission }, requestCode);
        }
        else {

            new DownloadTask(My_data.this, Faba, Utils.downloadPdfUrl);

        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

    if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(My_data.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                new DownloadTask(My_data.this, Faba, Utils.downloadPdfUrl);

            } else {
                final SweetAlertDialog CC = new SweetAlertDialog(My_data.this, SweetAlertDialog.WARNING_TYPE);
                CC.setTitleText("Storage Permission Denied");
                CC.setContentText("Cannot Download without granting storage permission! If you keep seeing this go to settings application manager then permissions and grant this application permission for storage");
                CC.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                CC.setCancelable(false);
                CC.show();

                Snackbar snack = Snackbar.make(My_data.this.findViewById(android.R.id.content),"Storage Permission Denied",      Snackbar.LENGTH_LONG);
                SnackBarHelper.configSnackbar(My_data.this, snack);
                snack.show();

                Toast.makeText(My_data.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }




    //Open downloaded folder
    private void openDownloadedFolder() {
        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {

            //Get Download Directory File
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + Utils.downloadDirectory);

            //If file is not present then display Toast


                //If directory is present Open Folder

                /** Note: Directory will open only if there is a app to open directory like File Manager, etc.  **/

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/" + Utils.downloadDirectory);
                intent.setDataAndType(uri, "file/*");
                startActivity(Intent.createChooser(intent, "Open Download Folder"));


        } else
            Toast.makeText(My_data.this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

    }
}