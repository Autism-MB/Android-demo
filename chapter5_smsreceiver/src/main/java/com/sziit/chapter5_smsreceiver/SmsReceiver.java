package com.sziit.chapter5_smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class SmsReceiver extends BroadcastReceiver {
    /* 短信广播Action,该Action为Android隐藏Action无法通过帮助文件获取 */
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public String sTag;

    @Override
    public void onReceive(Context context, Intent intent) {
        sTag=getClass().toString();
        // 只处理短信接收类型的广播
        if (!SMS_RECEIVED_ACTION.equals(intent.getAction()))
            return;
        Log.d(sTag+":onReceive", "短信接收器成功接收到短信广播");
        // 获得外部数据
        Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;
        // 获取短信报文信息（满足短信议数据单元的数据类型）
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = getSmsMessages(pdus);
        //一次广播可能包含N条短信
        for (SmsMessage msg : messages) {
            EventData mEventData=new EventData();
            mEventData.setEventCode(EventData.SMSTYPE);
            mEventData.setStrMsg(msg.getMessageBody().toString());
            EventBus.getDefault().post(mEventData);

        }
    }
    /**
     * 将短信报文转换为短信对象     *
     * @param pdus 短信报文
     * @return 短信对象
     */
    private SmsMessage[] getSmsMessages(Object[] pdus) {
        // 创建短信对象数组
        SmsMessage[] messages = new SmsMessage[pdus.length];
        // 将短信报文转换为短信数据
        for (int i = 0; i < pdus.length; i++) {
            // 短信报文
            byte[] pdu = (byte[]) pdus[i];
            // 短信数据
            messages[i] = SmsMessage.createFromPdu(pdu);
        }
        return messages;
    }
}
