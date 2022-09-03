package com.emmanuel.emmanuelkorircv.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.emmanuel.emmanuelkorircv.Activities.File_viewer;
import com.emmanuel.emmanuelkorircv.Utility.AppConstants;
import com.emmanuel.emmanuelkorircv.Utility.Snackbar.SnackBarHelper;
import com.emmanuel.emmanuelkorircv.Utility.Snackbar.SnackBarHelper_Error;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.skydoves.progressview.ProgressView;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DownloadTask {
    private static final String TAG = "Download Task";
    private final Context context;
    private String downloadUrl = "";
    private String downloadFileName = "";
    private final String getDownloadDiretory = "";
    private final SweetAlertDialog pDialog;
    private final File dir_;
    private ProgressView progressView;
    FloatingActionButton Faba ;
    Activity activity ;

    public DownloadTask(Context context, FloatingActionButton buttonText, ProgressView progressView, String downloadUrl) {
        this.context = context;
        activity = (Activity) context;
        this.downloadUrl = downloadUrl;
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        downloadFileName = downloadUrl.replace(AppConstants.mainUrl, "").replace("%20", "");//Create file name by picking download file name from URL;
        this.Faba = buttonText;
        this.progressView = progressView;
        Log.e(TAG, downloadFileName);

        //create a new directory since in api 30+ Environment.getExternalStorageDirectory() is depcracated

        //make download dir if not existing
        dir_ = new File(context.getFilesDir(), AppConstants.downloadDirectory);
        if(!dir_.exists()) {
            dir_.mkdirs();
        }
       // Toast.makeText(context,dir_.getPath(),Toast.LENGTH_LONG+1000000).show();
        //Start Downloading Task
        checkforDownloadedFolder();
    }

    public DownloadTask(Context context,  String downloadUrl) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        downloadFileName = downloadUrl.replace(AppConstants.mainUrl, "").replace("%20", "");//Create file name by picking download file name from URL;
        Log.e(TAG, downloadFileName);

        //create a new directory since in api 30+ Environment.getExternalStorageDirectory() is depcracated

        //make download dir if not existing
        dir_ = new File(context.getFilesDir(), AppConstants.downloadDirectory);
        if(!dir_.exists()) {
            dir_.mkdirs();
        }
        // Toast.makeText(context,dir_.getPath(),Toast.LENGTH_LONG+1000000).show();
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
        String path = dir_.getPath()+ File.separator + downloadFileName;
        File file = new File(path);
        return file.exists();
    }



    //Open downloaded folder
    private void checkforDownloadedFolder() {
        if(isConnectingToInternet())
        {
         if(fileisFilePresent())
         {
           intentPdf();
         }
         else
         {
             InitDownloadTask();
         }
        }else
        {
            if(fileisFilePresent())
            {
                intentPdf();
            }else {
                Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Internet connection needed to fetch the document",Snackbar.LENGTH_SHORT);
                SnackBarHelper.configSnackbar(context, snack);
                snack.show();
            }
        }
    }


    public  void InitDownloadTask() {


        int downloadId = PRDownloader.download(downloadUrl, dir_.getPath(), downloadFileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        progressView.setVisibility(View.VISIBLE);
                        progressView.setProgress(0);
                        progressView.setLabelText("Downloading.. "+"0%");
                        Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Started Download",      Snackbar.LENGTH_SHORT);
                        SnackBarHelper.configSnackbar(context, snack);
                        snack.show();

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        progressView.setProgress((float)(progress.currentBytes/progress.totalBytes)*100);
                        progressView.setLabelText("Downloading.. "+(progress.currentBytes/progress.totalBytes)*100+"%");
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Download Complete",      Snackbar.LENGTH_SHORT);
                        SnackBarHelper.configSnackbar(context, snack);
                        snack.show();
                        progressView.setVisibility(View.INVISIBLE);
                      intentPdf();
                    }

                    @Override
                    public void onError(Error error) {
                      if(error.isConnectionError())
                      {
                          Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Connection Error",Snackbar.LENGTH_SHORT);
                          SnackBarHelper_Error.configSnackbar(context, snack);
                          snack.show();
                      }
                      else if (error.isServerError())
                      {
                          Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Server Error", Snackbar.LENGTH_SHORT);
                          SnackBarHelper_Error.configSnackbar(context, snack);
                          snack.show();
                      }
                      else
                      {
                          Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content),"Unknown Error",Snackbar.LENGTH_SHORT);
                          SnackBarHelper_Error.configSnackbar(context, snack);
                          snack.show();
                      }

                      progressView.setVisibility(View.INVISIBLE);

                    }

                });

       // PRDownloader.resume(downloadId);

    }

    private void intentPdf()
    {
        Intent intent = new Intent(context, File_viewer.class);
        intent.putExtra("File_Name",downloadFileName);
        ( context).startActivity(intent);
    }

}
