package com.emmanuel.emmanuelkorircv.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.emmanuel.emmanuelkorircv.File_viewer;
import com.emmanuel.emmanuelkorircv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.Inflater;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DownloadTask {
    private static final String TAG = "Download Task";
    private final Context context;
    private String downloadUrl = "", downloadFileName = "";
    private final SweetAlertDialog pDialog;

    public DownloadTask(Context context, FloatingActionButton buttonText, String downloadUrl) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        downloadFileName = downloadUrl.replace(Utils.mainUrl, "").replace(Utils.mainUrltoken, "").replace("%20", "");//Create file name by picking download file name from URL;
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        checkforDownloadedFolder();

    }


    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public boolean fileisFilePresent() {
        String path = Environment.getExternalStorageDirectory() + "/"
                + Utils.downloadDirectory+ "/" + downloadFileName;
        File file = new File(path);
        return file.exists();
    }



    //Open downloaded folder
    private void checkforDownloadedFolder() {


        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {

            //Get Download Directory File
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + Utils.downloadDirectory);

            //If file is not present then display Toast
            if (apkStorage.exists()&&(fileisFilePresent() )) {

                Intent intent = new Intent(context, File_viewer.class);
                ((Activity)context).startActivity(intent);



            }
            else if (apkStorage.exists() && (!fileisFilePresent() ))
            {

                if (isConnectingToInternet()) {
                    new DownloadingTask().execute();

                }
                else
                {
                    //Context find view by id declaration
                    View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);

                    Snackbar snack = Snackbar.make(rootView.findViewById(android.R.id.content),"Check internet connection!",      Snackbar.LENGTH_LONG);
                    SnackBarHelper.configSnackbar(context, snack);
                    snack.show();


                    final SweetAlertDialog CC = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    CC.setCustomImage(R.drawable.ic_network_error);
                    CC.setTitleText("No internet Connection");
                    CC.setContentText("Check network!");
                    CC.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    CC.setCancelable(false);
                    CC.show();
                }

            }
            else {
                if (isConnectingToInternet()) {
                    new DownloadingTask().execute();

                }
                else
                {
                    //Context find view by id declaration
                    View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);

                    Snackbar snack = Snackbar.make(rootView.findViewById(android.R.id.content),"Check internet connection!",      Snackbar.LENGTH_LONG);
                    SnackBarHelper.configSnackbar(context, snack);
                    snack.show();


                    final SweetAlertDialog CC = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    CC.setCustomImage(R.drawable.ic_network_error);
                    CC.setTitleText("No internet Connection");
                    CC.setContentText("Check network!");
                    CC.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    CC.setCancelable(false);
                    CC.show();
                }

            }

        } else {
            Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();
            final SweetAlertDialog CC = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
            CC.setCustomImage(R.drawable.ic_storage);
            CC.setTitleText("No Storage found");
            CC.setContentText("No storage media found");
            CC.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            CC.setCancelable(false);
            CC.show();
        }

    }



    private class DownloadingTask extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();

            Inflater inflater = null;

            //Context find view by id declaration
            View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);

            //context calling of rootview
            Snackbar snack = Snackbar.make(rootView,"Downloading my RESUME!",      Snackbar.LENGTH_LONG);
            SnackBarHelper.configSnackbar(context, snack);
            snack.show();
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Downloading file");
            pDialog.setCancelable(false);
            pDialog.show();




        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    Toast.makeText(context,R.string.downloadCompleted,Toast.LENGTH_SHORT).show();
                   pDialog.dismissWithAnimation();
                    new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("File Downloaded")
                            .setContentText("My Resume downloaded Successfuly to storage on the folder " +
                                    "[Emmanuel Korir CV] in pdf format. Do you want to open it?")
                            .setCancelText("No!")
                            .setConfirmText("Yes")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    Intent intent = new Intent(context, File_viewer.class);
                                    ((Activity)context).startActivity(intent);
                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();

                } else {
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("File Download failed")
                            .setContentText("Download Failed error")
                            .setCancelText("ok")
                            .showCancelButton(true)

                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();

                    Toast.makeText(context,R.string.downloadFailed,Toast.LENGTH_SHORT).show();
                    pDialog.dismissWithAnimation();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context,R.string.downloadAgain,Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();
                pDialog.dismissWithAnimation();

                //Change button text if exception occurs
                Toast.makeText(context,R.string.downloadFailed,Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("File Download failed")
                                .setContentText("Download Failed Retry?")
                                .setCancelText("No!")
                                .setConfirmText("Yes")
                                .showCancelButton(true)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        new DownloadingTask().execute();
                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                })
                                .show();

                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }





        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }


                //Get File if SD card is present
                if (new CheckForSDCard().isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/"
                                    + Utils.downloadDirectory);
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }





    }
}
