package com.sziit.chapter5_3anotherbroadcast;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sziit.chapter5_3anotherbroadcast.receiver.MyBroadcastReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private static String BroadName = "MY_BROADCAST";//定义广播消息名称
    private MyBroadcastReceiver myBroadcastReceiver;//定义广播接收器
    private TextView mTVInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initReceiver();
        initEventBus();
    }

    private void initEventBus() {
        EventBus.getDefault().register(this);//将MainActivity注册到EventBus
    }

    private void initReceiver() {
        myBroadcastReceiver = new MyBroadcastReceiver();//初始化广播接收器对象
        IntentFilter intentFilter = new IntentFilter(BroadName);//新键自定义广播过滤器
        intentFilter.setPriority(110);
        registerReceiver(myBroadcastReceiver, intentFilter);//设置广播接收器接受自定义广播
    }

    private void initView() {
        mTVInfo = (TextView) findViewById(R.id.TV_info);
    }
    @Subscribe
    public void onEventMainThread(Object b){
        mTVInfo.setText((String)b);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
