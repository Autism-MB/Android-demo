package com.sziit.firstactivity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG=(getPackageName()+"\\"+getLocalClassName()).toString();
        Log.d(TAG,"onCreate：肖茂彬+1907040231");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState：肖茂彬+1907040231");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState：肖茂彬+1907040231");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart：肖茂彬+1907040231");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart：肖茂彬+1907040231");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop：肖茂彬+1907040231");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy：肖茂彬+1907040231");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume：肖茂彬+1907040231");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause：肖茂彬+1907040231");
    }

}
