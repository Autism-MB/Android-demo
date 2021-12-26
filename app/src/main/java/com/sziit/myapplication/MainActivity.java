package com.sziit.myapplication;


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
        String Tag = getLocalClassName().toString();
        Log.v(Tag,"这里输出的是冗余信息");
        Log.d(Tag,"这里输出的是调试信息");
        Log.i(Tag,"这里输出的是警告信息");
        Log.w(Tag,"这里输出的是普通信息");
        Log.e(Tag,"这里输出的是错误信息");
    }
}
