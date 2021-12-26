package com.sziit.chapter7_4contact;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsContract extends AppCompatActivity {

    private TextView mTextView2;
    private TextView mTvName;
    private TextView mTvPhonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
        initData();
    }

    private void initData() {
        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        mTvName.setText(name);
        mTvPhonenumber.setText(number);
    }

    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPhonenumber = (TextView) findViewById(R.id.tv_phonenumber);
    }
}
