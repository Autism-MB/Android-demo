package com.sziit.chapter5_1phonestatus;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sziit.chapter5_1phonestatus.bean.EventBean;
import com.sziit.chapter5_1phonestatus.receiver.BatterLevelBroadcastReceiver;
import com.sziit.chapter5_1phonestatus.receiver.NetworkBroadcastReceiver;
import com.sziit.chapter5_1phonestatus.receiver.PowerConnectReceiver;
import com.sziit.chapter5_1phonestatus.receiver.PowerDisconnectReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView2;
    private TextView mTvBattery;
    private ProgressBar mPbBattery;
    private TextView mTvConnect;
    private ImageView mImgNetwork;
    private TextView mTvChange;
    private ImageView mCharge;
    private PowerConnectReceiver mPowerConnectReceiver;
    private PowerDisconnectReceiver mPowerDisconnectReceiver;
    private BatterLevelBroadcastReceiver batterLevelBroadcastReceiver;//定义广播接收器对象
    private NetworkBroadcastReceiver networkBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEventBus();
        initReceiver();
    }

    private void initEventBus() {
        //将MainActivity获得注册到EventBus上
        EventBus.getDefault().register(this);
    }

    private void initReceiver() {
        batterLevelBroadcastReceiver = new BatterLevelBroadcastReceiver();//初始化广播接收器对象
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);//新建电量变化广播过滤器
        super.registerReceiver(batterLevelBroadcastReceiver, intentFilter);//设置广播接收器接受电量广播

        networkBroadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter intentFilter2 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        super.registerReceiver(networkBroadcastReceiver, intentFilter2);

        mPowerConnectReceiver = new PowerConnectReceiver();
        IntentFilter intentFilter3 = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        super.registerReceiver(mPowerConnectReceiver, intentFilter3);

        mPowerDisconnectReceiver = new PowerDisconnectReceiver();
        IntentFilter intentFilter4 = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        super.registerReceiver(mPowerDisconnectReceiver, intentFilter4);
    }

    private void initView() {
        mTvBattery = (TextView) findViewById(R.id.tv_battery);
        mPbBattery = (ProgressBar) findViewById(R.id.pb_battery);
        mPbBattery.setProgress(60);
        mTvConnect = (TextView) findViewById(R.id.tv_connect);
        mImgNetwork = (ImageView) findViewById(R.id.img_network);
        mTvChange = (TextView) findViewById(R.id.tv_change);
        mCharge = (ImageView) findViewById(R.id.charge);
    }

    // ctrl+o

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    //onEventMainThread消息处理方法
    public void onEventMainThread(EventBean eventBean) {
        //eventBean.getiCode()方法判断是否有电量变化广播消息
        switch (eventBean.getiCode()) {
            case 1:
                mTvBattery.setText("电量: " + eventBean.getiData() + "%");
                mPbBattery.setProgress(eventBean.getiData());
                break;
            case 2:
                if (eventBean.getiData() == 0) {
                    mTvConnect.setText("网络状态：无网络");
                    mImgNetwork.setImageResource(R.drawable.w3);
                } else if (eventBean.getiData() == 1) {
                    mTvConnect.setText("网络状态：wifi连接");
                    mImgNetwork.setImageResource(R.drawable.w2);
                } else if (eventBean.getiData() == 2) {
                    mTvConnect.setText("网络状态：4G");
                    mImgNetwork.setImageResource(R.drawable.w1);
                } else if (eventBean.getiData() == 3) {
                    mTvConnect.setText("网络状态：4G");
                    mImgNetwork.setImageResource(R.drawable.w1);
                } else {
                    mTvConnect.setText("网络状态：未知");
                    mImgNetwork.setImageResource(R.drawable.w3);
                }
                break;
            case 3:
                if (eventBean.isBcharge()) {
                    mTvChange.setText("电池状态：正在充电");
                    mCharge.setImageResource(R.drawable.charge);
                } else {
                    mTvChange.setText("电池状态：没有充电");
                    mCharge.setImageResource(R.drawable.uncharge);
                }
                break;
            default:
                break;
        }
    }

}
