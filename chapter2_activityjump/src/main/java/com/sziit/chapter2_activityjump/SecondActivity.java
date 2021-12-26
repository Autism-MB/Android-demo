package com.sziit.chapter2_activityjump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView1;
    private TextView mTextView2;
    private Button mBtnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }
    private String strPara;
    private void initData() {
        Intent mIntent = getIntent();
        strPara = mIntent.getStringExtra("user");
        mTextView2.setText("第一个Activity传递的参数user:"+strPara);
    }

    private void initView() {
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mBtnJump = (Button) findViewById(R.id.btn_jump);

        mBtnJump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                jumpToFirstActivity();
                break;
        }
    }
    private void jumpToFirstActivity() {
        Intent mIntent=new Intent();
        mIntent.putExtra("password","sziit");
        setResult(RESULT_OK,mIntent);
        finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        jumpToFirstActivity();
    }
}