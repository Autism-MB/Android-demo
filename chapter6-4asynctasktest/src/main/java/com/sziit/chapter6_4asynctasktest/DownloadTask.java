package com.sziit.chapter6_4asynctasktest;

import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import java.nio.file.FileAlreadyExistsException;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;
    private static final int TYPE_DATA = 4;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;
    private int iPercent = 0;

    public DownloadTask(){

    }

    @Override
    protected Integer doInBackground(String... strings) {
        try{
            while (true){
                if (isCanceled){
                    return TYPE_CANCELED;
                }else if(isPaused){
                    continue;
                }
                int iDownLoadPercent=getDownloadProgress();
                publishProgress(iDownLoadPercent);
                if (iDownLoadPercent>=100){
                    break;
                }
                Thread.sleep(100);
            }
        }catch (Exception e){
            e.printStackTrace();
            return TYPE_FAILED;
        }
        return TYPE_SUCCESS;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress=values[0];
        if (progress > lastProgress){
            sendEventData(TYPE_DATA,progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        sendEventData(integer,0);
    }
    public void startDownload(){
        isPaused = false;
        isCanceled = false;
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cannelDownload(){
        isCanceled = true;
    }

    void sendEventData(int iType, int iProgress) {
        EventData mEventData=new EventData();
        mEventData.setiType(iType);
        mEventData.setiProgress(iProgress);
        EventBus.getDefault().post(mEventData);
    }

    private int getDownloadProgress() {
        iPercent++;
        return iPercent;
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
    }
}
