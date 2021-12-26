package com.sziit.chapter10_2sqlite.bean;

public class UserInfo {
    private String sName;
    private int iNumber;
    private String strClass;
    private String strHobby;
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public int getiNumber() {
        return iNumber;
    }
    public void setiNumber(int iNumber) {
        this.iNumber = iNumber;
    }
    public String getStrClass() {
        return strClass;
    }
    public void setStrClass(String strClass) {
        this.strClass = strClass;
    }
    public String getStrHobby() {
        return strHobby;
    }
    public void setStrHobby(String strHobby) {
        this.strHobby = strHobby;
    }
}
