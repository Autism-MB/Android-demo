package com.sziit.chapter6_4asynctasktest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {
    private Context mCtx;
            private DownloadBinder mDownloadBinder=new DownloadBinder(this);

    @Override
    public void onCreate() {

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBinder;
    }
}




