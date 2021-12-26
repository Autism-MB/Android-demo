package com.sziit.chapter5_3anotherbroadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String TAG=context.getPackageName().toString();
        String strName=intent.getStringExtra("name");
        String strId=intent.getStringExtra("id");
        String strClass=intent.getStringExtra("class");
        String strMsg=TAG+";name:"+strName+";id:"+strId+";class"+strClass;
        Log.d(TAG,strMsg);
        EventBus.getDefault().post(strMsg);//发送数据
//        Toast.makeText(context,strMsg,Toast.LENGTH_LONG).show();//收到广播消息后提示

    }
}
