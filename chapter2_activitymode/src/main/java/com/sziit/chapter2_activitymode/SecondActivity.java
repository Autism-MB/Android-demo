package com.sziit.chapter2_activitymode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView1;
    private Button mBtnJumptoThird;
    private String TAG;
    private static int iActNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        iActNum +=1;
        Log.d(TAG,"第" +iActNum+ "个Activity" + "onCreate: ");
    }

    private void initView() {
        TAG = getLocalClassName().toString();
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mBtnJumptoThird = (Button) findViewById(R.id.btn_jumptoThird);

        mBtnJumptoThird.setOnClickListener(this);
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
            case R.id.btn_jumptoThird:
                Intent intent = new Intent(this,ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
