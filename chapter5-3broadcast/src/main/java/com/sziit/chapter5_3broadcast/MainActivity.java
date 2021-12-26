package com.sziit.chapter5_3broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sziit.chapter5_3broadcast.receiver.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSendbroadcast;
    private static String BroadName = "MY_BROADCAST";//定义广播消息名称
    private MyBroadcastReceiver myBroadcastReceiver;//定义广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initReceiver();
    }

    private void initReceiver() {
        myBroadcastReceiver=new MyBroadcastReceiver();//初始化广播接收器对象
        IntentFilter intentFilter=new IntentFilter(BroadName);//新键自定义广播过滤器
        registerReceiver(myBroadcastReceiver,intentFilter);//设置广播接收器接受自定义广播
    }

    private void initView() {
        mBtnSendbroadcast = (Button) findViewById(R.id.btn_sendbroadcast);
        mBtnSendbroadcast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendbroadcast:
                sendBroadcast();
                break;
        }
    }

    private void sendBroadcast() {
        Intent intent = new Intent(BroadName);//创建广播意图
        //intent表示广播意图包含了广播接收Action(频道)，包含了广播数据
        intent.putExtra("name","肖茂彬");
        intent.putExtra("id","1907040231");
        intent.putExtra("class","19互联3-2");
        sendOrderedBroadcast(intent,null);//发送广播
    }
}
