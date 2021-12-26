package com.sziit.chapter6_3service;

import android.os.Binder;
import android.util.Log;

public class DownloadBinder extends Binder {
    public void startDownload(){
        Log.d("肖茂彬 1907040231", "startDownload: 开始下载");
    }

    public int getDownload(){
        Log.d("肖茂彬 1907040231", "getDownload: 下载进度:10%");
        return 10;
    }
}
