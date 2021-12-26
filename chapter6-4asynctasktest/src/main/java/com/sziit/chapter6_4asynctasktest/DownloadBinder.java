package com.sziit.chapter6_4asynctasktest;

import android.content.Context;
import android.os.Binder;
import android.widget.Toast;

public class DownloadBinder extends Binder {
    private Context mCtx;
    private DownloadTask mDownloadTask;

    public DownloadBinder(Context mCtx){
        this.mCtx=mCtx;
    }
    public void startDownload(String url){
        if (mDownloadTask == null){
            mDownloadTask = new DownloadTask();
            mDownloadTask.execute(url);
            Toast.makeText(mCtx,"下载中....",Toast.LENGTH_SHORT).show();
        }else {
            mDownloadTask.startDownload();
            Toast.makeText(mCtx,"继续下载...",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelDownload(){
        if (mDownloadTask != null){
            mDownloadTask.cannelDownload();
        }else {
            Toast.makeText(mCtx,"Canneled",Toast.LENGTH_SHORT).show();
        }
        mDownloadTask = null;
    }
    public void puaseDownload(){
        if (mDownloadTask != null){
            mDownloadTask.pauseDownload();
        }
    }
}
