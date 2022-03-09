package com.emmanuel.emmanuelkorircv;

import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.calcCpuCoreCount;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.externalMemoryAvailable;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.formatSize;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.formatSizeRAM;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.getAvailableExternalMemorySize;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.getAvailableInternalMemorySize;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.getTotalExternalMemorySize;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.getTotalInternalMemorySize;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.takeCurrentCpuFreq;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.takeMaxCpuFreq;
import static com.emmanuel.emmanuelkorircv.Utility.DeviceInfo.thermalTemp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.emmanuel.emmanuelkorircv.Utility.CheckForSDCard;
import com.emmanuel.emmanuelkorircv.Utility.DownloadTask;
import com.emmanuel.emmanuelkorircv.Utility.MyApplication;
import com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences;
import com.emmanuel.emmanuelkorircv.Utility.SnackBarHelper;
import com.emmanuel.emmanuelkorircv.Utility.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.WriterException;
import com.pddstudio.urlshortener.URLShortener;
import com.polyak.iconswitch.IconSwitch;
import com.ramijemli.percentagechartview.PercentageChartView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class Preferences extends AppCompatActivity {
    private static TextView internetStatus;
    private LinearLayout Share,Sharelink,Sharecv;
    private CardView ShareCard,SharelinkCard,ShareCvcard;
    private  TextView Modelinfo,WebLInk,ResumeLink,AppLink,ShareText,ShareText1,ShareText2,ShareText3,ShareText4,sharetext,sharetext1,sharetext2,sharetext3,sharetext4,Text1,WebQR,ResumeQr,ApplinkQR,ScanQR1,ScanQr2,ScanQR3;
    private ImageView Conn,Disconn,Back,AppQrCode;
    private CircleImageView Con,Discon;
    private Animation animblink,animblink_link,nimbounce;
    private FloatingActionButton Float;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private UiModeManager uiModeManager;
    private IconSwitch iconSwitch;
    private LinearLayout CardlinearLy;
    private View View1,View2,View3,View4,View5,View6,View7,View8,View9,View10;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    private FirebaseFirestore db;
    private String AppqrString,ApplinkStr;
    private SharedPreferences sharedPreferences;
    private Map<String ,Object> userData;
    private PercentageChartView FloatViewRAM,FloatCPU,FloatStarage,FloatCPUFrq0,FloatCPUFrq1,FloatCPUFrq2,FloatCPUFrq3,FloatCPUFrq4,FloatCPUFrq5,FloatCPUFrq6,FloatCPUFrq7;
    private Thread threadStats,ThredStatsCpu;
    private  TextView TempCpu0,TempCpu1,TempCpu2,TempCpu3,TempCpu4,TempCpu5,TempCpu6,TempCpu7;
    private LinearLayout CPURow2,LinearLayoutstorage;
    private Handler handler1,handlerStats;
    private Runnable runnableCode1,runnableCodeStats;
    private ProgressView InternalStorage,ExternalStorage,RAMProgressview;
    private TextView InternalStorageText,ExternalStorageText,RAMText;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//using two to chang theme with this one and the other to change backgrounds for layouts
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
        }else{
            setTheme(R.style.AppTheme);
        }


        setContentView(R.layout.activity_preferences);




        Con= findViewById(R.id.online_dot);
        Discon=findViewById(R.id.offline_dot);
        Back=findViewById(R.id.back_button_preferences);
        internetStatus = (TextView) findViewById(R.id.internet_status);
        Share=findViewById(R.id.Shareapk);
        ShareCard=findViewById(R.id.Share_card);
        Sharelink=findViewById(R.id.Sharelink);
        SharelinkCard=findViewById(R.id.Sharelik_card);
        Sharecv=findViewById(R.id.ShareCv);
        ShareCvcard=findViewById(R.id.ShareCV_card);
        Float=findViewById(R.id.floatingActionButtonpref);
        WebLInk=findViewById(R.id.website_link_text);
        ResumeLink=findViewById(R.id.appliation_link_text);
        AppLink=findViewById(R.id.resume_link_text);
        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        ShareText=findViewById(R.id.Share_Text);
        ShareText1=findViewById(R.id.Share_Text1);
        ShareText2=findViewById(R.id.Share_Text2);
        ShareText3=findViewById(R.id.Share_Text3);
        ShareText4=findViewById(R.id.Share_Text4);
        sharetext=findViewById(R.id.share_text);
        sharetext1=findViewById(R.id.share_text1);
        sharetext2=findViewById(R.id.share_text2);
        sharetext3=findViewById(R.id.share_text3);
        sharetext4=findViewById(R.id.share_text4);
        View2=findViewById(R.id.view2);
        View3=findViewById(R.id.view3);
        View4=findViewById(R.id.view4);
        View5=findViewById(R.id.view5);
        View6=findViewById(R.id.view6);
        View7=findViewById(R.id.view7);
        View2=findViewById(R.id.view2);
        WebQR=findViewById(R.id.website_qr_code);
        ResumeQr=findViewById(R.id.resume_qr_code);
        ApplinkQR=findViewById(R.id.applink_qr_code);
        ScanQR1=findViewById(R.id.scan_qr1);
        ScanQr2=findViewById(R.id.scan_qr2);
        ScanQR3=findViewById(R.id.scan_qr3);
        AppQrCode=findViewById(R.id.Application_download_qrcode);
        db=FirebaseFirestore.getInstance();
        sharedPreferences=getSharedPreferences(Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);

        userData=new HashMap<>();

        FloatViewRAM=findViewById(R.id.view_ram_float);
        FloatCPU=findViewById(R.id.view_cpu_float);
        FloatStarage=findViewById(R.id.view_storage_float);
        FloatCPUFrq0=findViewById(R.id.view_cpu_float_freq0);
        FloatCPUFrq1=findViewById(R.id.view_cpu_float_freq1);
        FloatCPUFrq2=findViewById(R.id.view_cpu_float_freq2);FloatCPUFrq0=findViewById(R.id.view_cpu_float_freq0);
        FloatCPUFrq3=findViewById(R.id.view_cpu_float_freq3);
        FloatCPUFrq4=findViewById(R.id.view_cpu_float_freq4);
        FloatCPUFrq5=findViewById(R.id.view_cpu_float_freq5);
        FloatCPUFrq6=findViewById(R.id.view_cpu_float_freq6);
        FloatCPUFrq7=findViewById(R.id.view_cpu_float_freq7);

        TempCpu0=findViewById(R.id.cpu_0_temp);
        TempCpu1=findViewById(R.id.cpu_1_temp);
        TempCpu2=findViewById(R.id.cpu_2_temp);
        TempCpu3=findViewById(R.id.cpu_3_temp);
        TempCpu4=findViewById(R.id.cpu_4_temp);
        TempCpu5=findViewById(R.id.cpu_5_temp);
        TempCpu6=findViewById(R.id.cpu_6_temp);
        TempCpu7=findViewById(R.id.cpu_7_temp);

        CPURow2=findViewById(R.id.Linear_Layout_cpu_Row_2);

        InternalStorage=findViewById(R.id.progressViewInternalStorage);
        ExternalStorage=findViewById(R.id.progressViewExternalStorage);
        RAMProgressview=findViewById(R.id.progressViewRAM);
        InternalStorageText=findViewById(R.id.internal_storage_Text);
        ExternalStorageText=findViewById(R.id.External_storage_Text);
        RAMText=findViewById(R.id.RAM_Text);

        LinearLayoutstorage=findViewById(R.id.linear_layout_storage_info);


        animblink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink_infinite);
        animblink_link = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        // At activity startup we manually check the internet status and change
        // the text status


        // If the night mode is correct by default, it sets the Night Mode theme.
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NoActionBar);
            LinearLayout layout =(LinearLayout) findViewById(R.id.layout_info_card);
            layout.setBackgroundResource(R.drawable.gradient_card_dark);
            LinearLayout layout1 =(LinearLayout) findViewById(R.id.layout_info_card1);
            layout1.setBackgroundResource(R.drawable.gradient_card_dark);
            LinearLayout layout2 =(LinearLayout) findViewById(R.id.layout_info_card2);
            layout2.setBackgroundResource(R.drawable.gradient_card_dark);
            LinearLayout layout3 =(LinearLayout) findViewById(R.id.layout_info_card3);
            layout3.setBackgroundResource(R.drawable.gradient_card_dark);
            Drawable res = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_white);
            Back.setImageDrawable(res);
            LinearLayout layout4 =(LinearLayout) findViewById(R.id.layout_info_card4);
            layout4.setBackgroundResource(R.drawable.gradient_card_dark);
            LinearLayout layout5 =(LinearLayout) findViewById(R.id.layout_info_card5);
            layout5.setBackgroundResource(R.drawable.gradient_card_dark);
            ConstraintLayout layoutmain =(ConstraintLayout) findViewById(R.id.background_layout);
            layoutmain.setBackgroundColor(Color.parseColor("#4E4E4E"));
            WebLInk.setTextColor(Color.parseColor("#FFFFFF"));
            AppLink.setTextColor(Color.parseColor("#FFFFFF"));
            ResumeLink.setTextColor(Color.parseColor("#FFFFFF"));
            ShareText.setTextColor(Color.parseColor("#FFFFFF"));
            ShareText1.setTextColor(Color.parseColor("#FFFFFF"));
            ShareText2.setTextColor(Color.parseColor("#FFFFFF"));
            ShareText3.setTextColor(Color.parseColor("#FFFFFF"));
            ShareText4.setTextColor(Color.parseColor("#FFFFFF"));
            sharetext.setTextColor(Color.parseColor("#FFFFFF"));
            sharetext1.setTextColor(Color.parseColor("#FFFFFF"));
            sharetext2.setTextColor(Color.parseColor("#FFFFFF"));
            sharetext3.setTextColor(Color.parseColor("#FFFFFF"));
            sharetext4.setTextColor(Color.parseColor("#FFFFFF"));
            WebQR.setTextColor(Color.parseColor("#FFFFFF"));
            ApplinkQR.setTextColor(Color.parseColor("#FFFFFF"));
            ResumeQr.setTextColor(Color.parseColor("#FFFFFF"));
            ScanQR1.setTextColor(Color.parseColor("#FFFFFF"));
            ScanQr2.setTextColor(Color.parseColor("#FFFFFF"));
            ScanQR3.setTextColor(Color.parseColor("#FFFFFF"));
            //Text1.setTextColor(Color.parseColor("#FFFFFF"));
            View2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View4.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View5.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View6.setBackgroundColor(Color.parseColor("#FFFFFF"));
            View7.setBackgroundColor(Color.parseColor("#FFFFFF"));
            LinearLayoutstorage.setBackgroundColor(Color.parseColor("#3A3B3C"));
           // View8.setBackgroundColor(Color.parseColor("#FFFFFF"));
           // View9.setBackgroundColor(Color.parseColor("#FFFFFF"));
           // View10.setBackgroundColor(Color.parseColor("#FFFFFF"));
            FloatViewRAM.setBackgroundColor(R.color.white);
            FloatCPU.setBackgroundColor(R.color.white);
            FloatStarage.setBackgroundColor(R.color.white);

            FloatViewRAM.setTextColor(Color.parseColor("#FFFFFF"));

        }else{
            setTheme(R.style.AppTheme);
        }
        //night mode end

        iconSwitch = (IconSwitch) findViewById(R.id.iconSwitchDarkLight);


        //If NightMode is active, the Switch is on right side. Enabled to be active
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            iconSwitch.setChecked(IconSwitch.Checked.RIGHT);
            iconSwitch.setBackgroundColor(getColor(R.color.black));
        }


        iconSwitch.setCheckedChangeListener(new IconSwitch.CheckedChangeListener()
        {
            @Override
            public void onCheckChanged(IconSwitch.Checked current) {
                //simple witch case
                switch (current) {
                    case LEFT:
                        //showing simple toast message to the user
                        handler1.removeCallbacks(runnableCode1);
                        handlerStats.removeCallbacks(runnableCodeStats);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        handler1.post(runnableCode1);
                        handlerStats.post(runnableCodeStats);
                        Toast.makeText(Preferences.this, "Light Mode", Toast.LENGTH_SHORT).show();
                        break;

                    case RIGHT:
                        //showing simple toast message to the user
                        handler1.removeCallbacks(runnableCode1);
                        handlerStats.removeCallbacks(runnableCodeStats);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        iconSwitch.setBackgroundColor(getColor(R.color.black));
                        Toast.makeText(Preferences.this, "Dark Mode", Toast.LENGTH_SHORT).show();
                        handler1.post(runnableCode1);
                        handlerStats.post(runnableCodeStats);
                        break;
                }
            }
        });

        // Create the Handler object (on the main thread by default)
        Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                // checks connection ever 100ms
             try {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                 changeTextStatus(networkInfo != null && networkInfo.isConnected());
            } catch (Exception e)

            {
                e.printStackTrace();
            }
                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 0.1 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 500);
            }
        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);











        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.this.onBackPressed();


            }
        });



        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApplication();


            }
        });
        ShareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApplication();


            }
        });



        Sharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLink();


            }
        });
        SharelinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLink();


            }
        });


        Sharecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCv();


            }
        });
        ShareCvcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCv();


            }
        });

        WebLInk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLInk.startAnimation(animblink_link);
                //animation lottie
                final Dialog sDialog =new Dialog(Preferences.this);
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://emmanuelkorircv.web.app/"));
                        startActivity(browserIntent);

                    }
                }, 4000);


            }
        });

       AppLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppLink.startAnimation(animblink_link);
                //animation lottie
                final Dialog sDialog =new Dialog(Preferences.this);
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApplinkStr));
                        startActivity(browserIntent);

                    }
                }, 4000);



            }
        });

        ResumeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResumeLink.startAnimation(animblink_link);
                //animation lottie
                final Dialog sDialog =new Dialog(Preferences.this);
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/emmanuelkorircv.appspot.com/o/CV%20EMMANUEL%20KORIR%20(edited).pdf?alt=media&token=39412dda-c542-4e83-8053-8c84919b6f4a"));
                        startActivity(browserIntent);

                    }
                }, 4000);


            }
        });






        String arch = System.getProperty("os.arch");
//        Log.v("Scan Class", getDeviceName());
//        Toast.makeText(this, getDeviceName(), Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.quantity_text);

        try {
        tv.setText("******DEVICE Information ******" + "\n");
        tv.append("Model: " + Build.MODEL + "\n");
        tv.append("Brand: " + Build.BRAND + "\n");
        tv.append("Manufacturer: " + Build.MANUFACTURER + "\n");
        tv.append("Device: " + Build.DEVICE + "\n");
        tv.append("Product: " + Build.PRODUCT + "\n");
        tv.append("Serial: " + Build.SERIAL + "\n");

        tv.append("\n" + "***** SOC *****" + "\n");
        tv.append("Hardware: " + Build.HARDWARE + "\n");
        tv.append("Number of CPU cores: " + getNumberOfCores() + "\n");
        tv.append("Architecture: " + arch +  "\n");


            //cpus frequency
            int CpuCurrentFreq;
            if(calcCpuCoreCount()>4)
            {
                //show row 2 in octacore
                CPURow2.setVisibility(View.VISIBLE);
            }

            // Create the Handler object (on the main thread by default)
            handlerStats = new Handler();
// Define the code block to be executed
            runnableCodeStats = new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {

                    ActivityManager actManager = (ActivityManager) Preferences.this.getSystemService(Context.ACTIVITY_SERVICE);
                    ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
                    assert actManager != null;
                    actManager.getMemoryInfo(memInfo);

                    float totalMemory = memInfo.totalMem;
                    float availMemory = memInfo.availMem;
                    float usedMemory = totalMemory - availMemory;
                    float perecentlong = (float) ((usedMemory / totalMemory)*100);

                    FloatViewRAM.setProgress(perecentlong,true);
                    RAMProgressview.setProgress(perecentlong);

                    RAMText.setText("Total: "+ formatSizeRAM(memInfo.totalMem) +"  Used: "+formatSizeRAM(memInfo.totalMem-memInfo.availMem)+"  Free: "+formatSizeRAM(memInfo.availMem));

                 //Toast.makeText(Preferences.this, calThermalsCount()+"",Toast.LENGTH_SHORT).show();

                    float usedinternalstorage = (float)(getTotalInternalMemorySize()-getAvailableInternalMemorySize());
                    float Storageinternal = (float)(usedinternalstorage/getTotalInternalMemorySize()*100);

                    //Toast.makeText(Preferences.this, getTotalInternalMemorySize()+"",Toast.LENGTH_SHORT).show();

                    InternalStorage.setAnimating(true);
                    InternalStorage.setProgress(Storageinternal);
                    InternalStorage.setLabelText("Internal Storage");

                    InternalStorageText.setText("Size: "+formatSize(getTotalInternalMemorySize())+"  used: "+formatSize(getTotalInternalMemorySize()-getAvailableInternalMemorySize())+"   Free: "+formatSize(getAvailableInternalMemorySize()));

                    if(externalMemoryAvailable())
                    {
                        float usedexternalstorage = (float)(getTotalExternalMemorySize()-getAvailableExternalMemorySize());
                        float Storageexternal = (float)(usedexternalstorage/getTotalExternalMemorySize()*100);
                        ExternalStorage.setAnimating(true);
                        ExternalStorage.setProgress(Storageexternal);
                        ExternalStorageText.setText("Size: "+formatSize(getTotalExternalMemorySize())+"  used: "+formatSize(getTotalExternalMemorySize()-getAvailableExternalMemorySize())+"   Free: "+formatSize(getAvailableExternalMemorySize()));

                    }else
                    {
                        ExternalStorageText.setVisibility(View.GONE);
                        ExternalStorage.setVisibility(View.GONE);
                    }

                    FloatStarage.setProgress(Storageinternal,true);

                    for (int i = 0; i <50; i++) {
                         switch (i)
                        {
                            case 17:
                                TempCpu0.setText(""+ thermalTemp(i));
                            break;
                            case 18:
                                TempCpu1.setText(""+ thermalTemp(i));
                                break;
                            case 19:
                                TempCpu2.setText(""+ thermalTemp(i));
                                break;
                            case 20:
                                TempCpu3.setText(""+ thermalTemp(i));
                                break;
                            case 21:
                                TempCpu4.setText(""+ thermalTemp(i));
                                break;
                            case 22:
                                TempCpu5.setText(""+ thermalTemp(i));
                                break;
                            case 23:
                                TempCpu6.setText(""+ thermalTemp(i));
                                break;
                            case 24:
                                TempCpu7.setText(""+ thermalTemp(i));
                                break;
                            default:
                                break;
                        }
                    }
                    handlerStats.postDelayed(this, 800);
                }
            };


            // Create the Handler object (on the main thread by default)
            handler1 = new Handler();
// Define the code block to be executed
            runnableCode1 = new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                   // -----------------------------------//cpus frequency--------------------------------
                    float percent =(float)(takeCurrentCpuFreq(0) / takeMaxCpuFreq(0) * 100);
                    FloatCPU.setProgress(percent, true);
                   // Toast.makeText(Preferences.this,takeCurrentCpuFreq(0)+" ",Toast.LENGTH_SHORT).show();
                    for (int i = 0; i <calcCpuCoreCount(); i++) {
                            float cpuload = (float)(takeCurrentCpuFreq(i)/takeMaxCpuFreq(i)*100);
                        switch (i)
                        {
                            case 0:
                                FloatCPUFrq0.setProgress(cpuload,true);
                            break;
                            case 1:
                                FloatCPUFrq1.setProgress(cpuload,true);
                                break;
                            case 2:
                                FloatCPUFrq2.setProgress(cpuload,true);
                                break;
                            case 3:
                                FloatCPUFrq3.setProgress(cpuload,true);
                                break;
                            case 4:
                                FloatCPUFrq4.setProgress(cpuload,true);
                                break;
                            case 5:
                                FloatCPUFrq5.setProgress(cpuload,true);
                                break;
                            case 6:
                                FloatCPUFrq6.setProgress(cpuload,true);
                                break;
                            case 7:
                                FloatCPUFrq7.setProgress(cpuload,true);
                                break;
                            default:
                                break;
                        }
                    }
                    //Toast.makeText(Preferences.this, String.valueOf(perecentlong),Toast.LENGTH_SHORT).show();
                    handler1.postDelayed(this, 100);
                }
            };

                handler.post(runnableCode1);
                handlerStats.post(runnableCodeStats);


// Start the initial runnable task by posting through the handler

        tv.append("\n" + "***** OS Information *****" + "\n");
        tv.append("Build release: " + Build.VERSION.RELEASE + "\n");
        tv.append("Incremental release: " + Build.VERSION.INCREMENTAL + "\n");
        tv.append("Base OS: " + Build.VERSION.BASE_OS + "\n");
        tv.append("CODE Name: " + Build.VERSION.CODENAME + "\n");
        tv.append("Security patch: " + Build.VERSION.SECURITY_PATCH + "\n");
        tv.append("Preview SDK: " + Build.VERSION.PREVIEW_SDK_INT + "\n");
        tv.append("SDK/API version: " + Build.VERSION.SDK_INT + "\n");
        tv.append("Display build: " + Build.DISPLAY + "\n");
        tv.append("Finger print: " + Build.FINGERPRINT + "\n") ;
        tv.append("Build ID: " + Build.ID + "\n");

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        String date = sdf.format(Build.TIME);

        tv.append("Build Time: " + date + "\n");
        tv.append("Build Type: " + Build.TYPE + "\n");
        tv.append("Build User: " + Build.USER + "\n");
        tv.append("Bootloader: " + Build.BOOTLOADER + "\n");
        tv.append("Kernel version: " + System.getProperty("os.version") + "\n");

        //tv.append(pInfo.installLocation);
        //tv.append(getVMVersion());

        } catch (Exception e) {
            e.printStackTrace();
        }
        QrcodeAppend();

    }
    //dark mode


    private void QrcodeAppend()

    {

        db.collection("app")
                .document(Objects.requireNonNull("cv"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> map=document.getData();
                        assert map != null;

                        AppLink.setText(Objects.requireNonNull(map.get(Shared_Preferences.link)).toString());
                        ApplinkStr=Objects.requireNonNull(map.get(Shared_Preferences.link)).toString();
                        AppqrString = document.getString("link");
                        Log.d("TAG", "Link: " + AppqrString);

                        userData.put(Shared_Preferences.link, ApplinkStr);
                        sharedPreferences.edit()
                                .putString(Shared_Preferences.link,AppqrString)
                                .apply();



                        URLShortener.shortUrl(Objects.requireNonNull(map.get(Shared_Preferences.link)).toString(), new URLShortener.LoadingCallback() {
                            @Override
                            public void startedLoading() {
                                AppLink.setText("Loading...");
                            }

                            @Override
                            public void finishedLoading(@Nullable String shortUrl) {
                                //make sure the string is not null
                                if(shortUrl != null) AppLink.setText(shortUrl);
                                else AppLink.setText("Click here or Scan Qr to Download APK");

                            }
                        });



                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                        // initializing a variable for default display.
                        Display display = manager.getDefaultDisplay();

                        // creating a variable for point which
                        // is to be displayed in QR Code.
                        Point point = new Point();
                        display.getSize(point);

                        // getting width and
                        // height of a point
                        int width = point.x;
                        int height = point.y;

                        // generating dimension from width and height.
                        int dimen = width < height ? width : height;
                        dimen = dimen * 3 / 4;

                        // setting this dimensions inside our qr code
                        // encoder to generate our qr code.
                        qrgEncoder = new QRGEncoder(ApplinkStr, null, QRGContents.Type.TEXT, dimen);
                        try {
                            // getting our qrcode in the form of bitmap.
                            bitmap = qrgEncoder.encodeAsBitmap();
                            // the bitmap is set inside our image
                            // view using .setimagebitmap method.
                            AppQrCode.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            // this method is called for
                            // exception handling.
                            Log.e("Tag", e.toString());
                        }

                    }

                }
                else
                {
                    AppQrCode.setVisibility(View.INVISIBLE);
                    AppLink.setText("Internet connection needed to get app download link");

                }
            }
        });






    }


    private String floatForm(double d) {
        return String.format(java.util.Locale.US, "%.2f", d);
    }





    public int getNumberOfCores() {
        if(Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }else {
            //Code for old SDK values
            return 0;
            //return Runtime.getRuntime().availableProcessors();
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }


    public boolean fileisFilePresent() {
        String path = Environment.getExternalStorageDirectory() + "/"
                + Utils.downloadDirectory+ "/" + Utils .downloadFileName;
        File file = new File(path);
        return file.exists();
    }



    private void  ShareCv()
    {

        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("pdf/*");


        // Make sure you put example png image named myImage.png in your
        // directory
        String path = Environment.getExternalStorageDirectory() + "/"
                + Utils.downloadDirectory + "/" + Utils.downloadFileName;

        //workaround beyond exposed uri fixes uri exposed yeah:-)
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        File pdfFileToShare = new File(path);


        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {


            if (pdfFileToShare.exists()&&(fileisFilePresent() )) {

                Uri uri = Uri.fromFile(pdfFileToShare);
                share.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(share, "Share My CV!"));



            }
            else {


                new SweetAlertDialog(Preferences.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setCustomImage(R.drawable.ic_internet)
                        .setTitleText("Download my Cv document First")
                        .setContentText("Download my Cv pdf to share it allow permission for storage if prompted to continue")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

                            }
                        })
                        .show();

            }

        }

    }




    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Preferences.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Preferences.this, new String[] { permission }, requestCode);
        }
        else {


            new DownloadTask(Preferences.this, Float, Utils.downloadPdfUrl);




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
                Toast.makeText(Preferences.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                new DownloadTask(Preferences.this ,Float, Utils.downloadPdfUrl);

            } else {
                final SweetAlertDialog CC = new SweetAlertDialog(Preferences.this, SweetAlertDialog.WARNING_TYPE);
                CC.setTitleText("Storage Permission Denied");
                CC.setContentText("Cannot Access my CV without granting storage permission! Plase allow Storage permission to continue! If you keep seeing this go to settings application manager then permissions and grant this application permission for storage");
                CC.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                CC.setCancelable(false);
                CC.show();

                Snackbar snack = Snackbar.make(Preferences.this.findViewById(android.R.id.content),"Storage Permission Denied",      Snackbar.LENGTH_LONG);
                SnackBarHelper.configSnackbar(Preferences.this, snack);
                snack.show();

                Toast.makeText(Preferences.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }






    private void  ShareLink()
    {

        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "My webpage \uD83D\uDE00 ");
        share.putExtra(Intent.EXTRA_TEXT, "https://cariboudevs.com/");

        startActivity(Intent.createChooser(share, "Share My link with friends \uD83D\uDE00 !"));
    }



    //share apk
    private void shareApplication() {
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //workaround beyond exposed uri fixes uri exposed yeah:-)
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());


            //Make new directory in new location
            File tempFile = new File(getExternalCacheDir() +  "/"
                    + Utils.downloadDirectory+"/"+Utils.downloadDirectoryapk);
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "Share My CV \uD83D\uDE00 app via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to change the text status
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeTextStatus(boolean isConnected) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

            // Change status according to boolean value
            if (isConnected) {
                internetStatus.setText("Connected.");
                internetStatus.setTextColor(getColor(R.color.colorWhite));
                Con.setVisibility(View.VISIBLE);
                Con.setImageDrawable(getDrawable(R.color.white));
                Con.startAnimation(animblink);
                Discon.setVisibility(View.GONE);

            } else {
                internetStatus.setText("Disconnected.");
                internetStatus.setTextColor(getColor(R.color.message_bubble_grey));
                Con.setVisibility(View.GONE);
                Discon.setVisibility(View.VISIBLE);
            }

        }else{
            // Change status according to boolean value
            if (isConnected) {
                internetStatus.setText("Connected.");
                internetStatus.setTextColor(getColor(R.color.colorPrimaryDark));
                Con.setVisibility(View.VISIBLE);
                Con.startAnimation(animblink);
                Discon.setVisibility(View.GONE);

            } else {
                internetStatus.setText("Disconnected.");
                internetStatus.setTextColor(getColor(R.color.colorTextPlaceholder));
                Con.setVisibility(View.GONE);
                Discon.setVisibility(View.VISIBLE);
            }
        }


    }
    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();// On Pause notify the Application
        handler1.removeCallbacks(runnableCode1);
        handlerStats.removeCallbacks(runnableCodeStats);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.activityPaused();// On Pause notify the Application

        handler1.removeCallbacks(runnableCode1);
        handlerStats.removeCallbacks(runnableCodeStats);


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.activityResumed();// On Resume notify the Application


        internetStatus = (TextView) findViewById(R.id.internet_status);
        // At activity startup we manually check the internet status and change
        // the text status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        changeTextStatus(networkInfo != null && networkInfo.isConnected());
    }

}