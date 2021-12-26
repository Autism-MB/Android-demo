package com.sziit.chapter5_smsreceiver;

public class EventData {
    public static int SMSTYPE=1;
    //eventCode=1代表短消息；
    private int eventCode; //区分事件类型
    private String strMsg; //接受到的信息

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getStrMsg() {
        return strMsg;
    }

    public void setStrMsg(String strMsg) {
        this.strMsg = strMsg;
    }
}
