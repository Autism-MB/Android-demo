package com.sziit.chapter10_file;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedReadActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private TextView mTvFilecontent;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference_read);
        initView();
        initData();
    }

    private void initData() {
        mSharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mTvFilecontent = (TextView) findViewById(R.id.tv_filecontent);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                readData();
                break;
        }
    }

    private void readData() {
        String strContent="";
        strContent+="User:"+mSharedPreferences.getString("User", null)+"\n";
        strContent+="Password:"+mSharedPreferences.getString("Password", null)+"\n";
        mTvFilecontent.setText(strContent);
    }

}
