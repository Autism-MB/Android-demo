package com.sziit.chapter2_jump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView2;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mButton2 = (Button) findViewById(R.id.button2);

        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                jumpToMainActivity();
                break;
        }
    }

    private void jumpToMainActivity() {
        Intent mIntent = new Intent(this,MainActivity.class);
        startActivity(mIntent);
    }
}
