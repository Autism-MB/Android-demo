package com.sziit.chapter2_activitymode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText3;
    private Button mBtnJumptoSecond;
    private String TAG;
    private static int iActNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();
        iActNum +=1;
        Log.d(TAG,"第" +iActNum+ "个Activity" + "onCreate: ");
    }

    private void initView() {
        TAG = getLocalClassName().toString();
        mText3 = (TextView) findViewById(R.id.text3);
        mBtnJumptoSecond = (Button) findViewById(R.id.btn_jumptoSecond);

        mBtnJumptoSecond.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"第" +iActNum+ "个Activity" + "onDestroy: ");
        iActNum-=1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jumptoSecond:

                break;
        }
    }
}
