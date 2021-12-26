package com.sziit.chapter5_1phonestatus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sziit.chapter5_1phonestatus.bean.EventBean;

import org.greenrobot.eventbus.EventBus;

public class PowerDisconnectReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
            EventBean eventBean=new EventBean();
            eventBean.setiCode(3);
            eventBean.setBcharge(false);
            EventBus.getDefault().post(eventBean);
        }
    }
}
