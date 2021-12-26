package com.sziit.chapter5_1phonestatus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import com.sziit.chapter5_1phonestatus.bean.EventBean;
import org.greenrobot.eventbus.EventBus;

public class BatterLevelBroadcastReceiver extends BroadcastReceiver {
    //广播接受类BatterLevelBroadcastReceiver接受广播
    @Override
    public void onReceive(Context context, Intent intent) {
        // 1 get bundle broadcast data from intent.getExtras
        //intent.getExtras()方法取出广播数据
        Bundle bundle = intent.getExtras();
        // bundle data is a map type data
        int level = bundle.getInt(BatteryManager.EXTRA_LEVEL,-1);
        //BatteryManager.EXTRA_LEVEL取出电量绝对值
        int scale = bundle.getInt(BatteryManager.EXTRA_SCALE,-1);
        //BatteryManager.EXTRA_SCALE取出电量最大值
        int result = level*100/scale;
        EventBean eventBean = new EventBean();
        eventBean.setiCode(1);
        eventBean.setiData(result);
        EventBus.getDefault().post(eventBean);
    }
}
