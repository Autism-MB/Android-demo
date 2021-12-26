package com.sziit.chapter6_4asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask {

    private String strTAG = "MyAsyncTask";

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(strTAG,"dolnBackground in: "+objects);
        publishProgress(1);
        Log.i(strTAG,"dolnBackground out ");
        return "over";
    }

    @Override
    protected void onPostExecute(Object o){
        Log.i(strTAG,"onPostExecute: ");
    }

    @Override
    protected void onCancelled(){
        Log.i(strTAG,"onCancelled: ");
    }

    public void onPostExcute(String args3){
        Log.i(strTAG,"onPostExcute: "+args3);
    }

    @Override
    protected void onProgressUpdate(Object[] values){
        Log.i(strTAG,"onProgressUpdate: "+values[0]);
    }
}
