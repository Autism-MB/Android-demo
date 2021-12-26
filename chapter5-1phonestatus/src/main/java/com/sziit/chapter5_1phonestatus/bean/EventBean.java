package com.sziit.chapter5_1phonestatus.bean;

public class EventBean {
    int iCode; //数据类型
    int iData; //电量值
    boolean bcharge;
    public boolean isBcharge() {
        return bcharge;
    }

    public void setBcharge(boolean bcharge) {
        this.bcharge = bcharge;
    }

    public int getiCode() {
        return iCode;
    }

    public void setiCode(int iCode) {
        this.iCode = iCode;
    }

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }


}
