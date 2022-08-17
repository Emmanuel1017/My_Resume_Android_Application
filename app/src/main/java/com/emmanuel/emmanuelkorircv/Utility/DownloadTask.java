package com.emmanuel.emmanuelkorircv.Utility;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.emmanuel.emmanuelkorircv.File_viewer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    public DownloadTask(Context context, FloatingActionButton buttonText, String downloadUrl) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        downloadFileName = downloadUrl.replace(Utils.mainUrl, "").replace(Utils.mainUrltoken, "").replace("%20", "");//Create file name by picking download file name from URL;

        Log.e(TAG, downloadFileName);

        //create a new directory since in api 30+ Environment.getExternalStorageDirectory() is depcracated

        //make download dir if not existing
        dir_ = new File(context.getFilesDir(), Utils.downloadDirectory);
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
            Toast.makeText(context,"No connection",Toast.LENGTH_SHORT).show();
        }
    }


    public  void InitDownloadTask() {

        int downloadId = PRDownloader.download(downloadUrl, dir_.getPath(), downloadFileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Toast.makeText(context,"started download",Toast.LENGTH_SHORT).show();
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

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(context,"completed",Toast.LENGTH_SHORT).show();
                      intentPdf();
                    }

                    @Override
                    public void onError(Error error) {
                      if(error.isConnectionError())
                      {
                          Toast.makeText(context,"Connection Error",Toast.LENGTH_SHORT).show();
                      }
                      else if (error.isServerError())
                      {
                          Toast.makeText(context,"Server Error",Toast.LENGTH_SHORT).show();
                      }
                      else
                      {
                          Toast.makeText(context,"Error "+error.getResponseCode()+" "+error.getServerErrorMessage(),Toast.LENGTH_SHORT).show();
                      }

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
