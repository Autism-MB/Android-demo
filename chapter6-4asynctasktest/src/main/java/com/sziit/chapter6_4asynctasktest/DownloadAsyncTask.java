package com.sziit.chapter6_4asynctasktest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.VibrationEffect;
import android.widget.Toast;

public class DownloadAsyncTask extends AsyncTask<Void,Integer,Boolean>{

    private Context mCtx;
    private ProgressDialog mPProgressDialog;
    private int iPercebt = 0;

    public DownloadAsyncTask(Context mCtx){
        super();
        this.mCtx=mCtx;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        mPProgressDialog=ProgressDialog.show(mCtx,"下载","正在下载中...",true,false,null);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        mPProgressDialog.dismiss();
        if (aBoolean){
            Toast.makeText(mCtx,"下载成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(mCtx,"下载失败",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mPProgressDialog.setMessage("下载进度"+values[0]+"%");
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            while (true){
                int iDownLoadPercent=getDownloadProgress();
                publishProgress(iDownLoadPercent);
                if (iDownLoadPercent>=100){
                    break;
                }
                Thread.sleep(100);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getDownloadProgress() {
        iPercebt++;
        return iPercebt;
    }

}
