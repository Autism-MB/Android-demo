package com.sziit.chapter2_activitymode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnJump;
    private Button mBtnJumptoSecond;
    private String TAG;
    private static int iActNum = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        iActNum +=1;
        Log.d(TAG,"第" +iActNum+ "个Activity" + "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"第" +iActNum+ "个Activity" + "onRestart: ");
    }

    private void initView() {
        TAG = getLocalClassName().toString();
        mBtnJump = (Button) findViewById(R.id.btn_jump);
        mBtnJumptoSecond = (Button) findViewById(R.id.btn_jumptoSecond);

        mBtnJump.setOnClickListener(this);
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
            case R.id.btn_jump:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_jumptoSecond:
                Intent intent1 = new Intent(this,SecondActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
