package com.sziit.chapter6_3service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.sziit.chapter6_3service.MainActivity;
import com.sziit.chapter6_3service.R;

import java.util.concurrent.BlockingDeque;

public class MyService extends Service {
    private String TAG;

    public MyService() {
        TAG="肖茂彬 1907040231";
    }

    private DownloadBinder downloadBinder=new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind: ");
        return downloadBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        String channelId="123";
        String channelName="123";
        String channelDescription="123";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channelDescription);
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if(notificationManager!=null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,channelId);
        builder.setSmallIcon(R.drawable.doyin)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.doyin))
                .setContentTitle("肖茂彬 1907040231")
                .setContentText("今天作业做好了")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);
        startForeground(1,builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
