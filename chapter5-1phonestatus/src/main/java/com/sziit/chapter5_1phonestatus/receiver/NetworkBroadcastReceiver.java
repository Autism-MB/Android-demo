package com.sziit.chapter5_1phonestatus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.sziit.chapter5_1phonestatus.bean.EventBean;
import org.greenrobot.eventbus.EventBus;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())){
            int netType = getVPMType(context);
            EventBean eventBean = new EventBean();
            eventBean.setiCode(2);
            eventBean.setiData(netType);
            EventBus.getDefault().post(eventBean);
        }
    }
    //getVPMType方法判断网络类型并获取网络状态
    private int getVPMType(Context context) {
        int netType = 0; //无网络
        //getSystemService获取系统的网络管理器
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE
        );
        //getActiveNetworkInfo（）获取系统网络详细信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info==null){
            return netType;
        }
        //getType获取网络类型
        int nType = info.getType();
        if (nType==ConnectivityManager.TYPE_WIFI){
            netType = 1;
        }else if (nType==ConnectivityManager.TYPE_MOBILE){
            int subType = info.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(
                    Context.TELEPHONY_SERVICE
            );
            if (subType==TelephonyManager.NETWORK_TYPE_UMTS && !telephonyManager.isNetworkRoaming()){
                netType=2; //3G
            }else {
                netType=3; //4G
            }
        }
        return netType;
    }
}
