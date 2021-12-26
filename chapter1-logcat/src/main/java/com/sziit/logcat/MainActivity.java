package com.sziit.logcat;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogCatText();
    }

    private void LogCatText() {
        String TAG = getLocalClassName().toString();
        Log.v(TAG,"Log： 肖茂彬 1907040231");
        Log.d(TAG,"Log： 肖茂彬 1907040231");
        Log.i(TAG,"Log： 肖茂彬 1907040231");
        Log.w(TAG,"Log： 肖茂彬 1907040231");
        Log.e(TAG,"Log： 肖茂彬 1907040231");
    }
}

