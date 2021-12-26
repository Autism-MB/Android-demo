package com.sziit.chapter6_2notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSendchat;
    private Button mBtnSendsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initNotifychannel();
    }

    private void initNotifychannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId,channelName,importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance =NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId,channelName,importance);
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        //获取NotificationManager的系统通知服务管理类
        NotificationManager notificationManager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager !=null){
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initView() {
        mBtnSendchat = (Button) findViewById(R.id.btn_sendchat);
        mBtnSendsub = (Button) findViewById(R.id.btn_sendsub);

        mBtnSendchat.setOnClickListener(this);
        mBtnSendsub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendchat:
                sendChatMsg();
                break;
            case R.id.btn_sendsub:
                sendSubMsg();
                break;
        }
    }
    private void sendChatMsg(){
        Intent intent = new Intent(this, NotificationActivity.class);//新建跳转的Activity对象
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);//新建延迟PendingIntent对象
        //获取NotificationManager的系统通知服务管理类
        NotificationManager notificationManager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //NotificationCompat方法构造通知，新键Build构造方法
        Notification notification=new NotificationCompat
                .Builder(this,"chat")
                .setContentTitle("肖茂彬+1907040231")//通知标题
                .setContentText("今天作业做好了")//通知内容
                .setWhen(System.currentTimeMillis())//通知时间
                .setSmallIcon(R.drawable.doyin)//通知大小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.doyin))
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.GREEN, 1000, 1000)
                .setContentIntent(pi)
                .build();
        notificationManager.notify(1,notification);
    }

    private void sendSubMsg() {
    }
}
