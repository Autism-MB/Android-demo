package com.sziit.chapter10_file;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtName;
    private EditText mEtPsd;
    private CheckBox mCheckBoxPsd;
    private Button mBtnSave;


    private static SharedPreferences mSharedPreferences;
    private TextView mTvName;
    private TextView mTvNo;
    private Button mBtnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference_write);
        initView();
        initData();
    }

    private void initData() {
        mSharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        mEtName.setText(mSharedPreferences.getString("User", null));
        mEtPsd.setText(mSharedPreferences.getString("Password", null));
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPsd = (EditText) findViewById(R.id.et_psd);
        mCheckBoxPsd = (CheckBox) findViewById(R.id.checkBox_Psd);
        mBtnSave = (Button) findViewById(R.id.btn_save);

        mBtnSave.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvName.setOnClickListener(this);
        mTvNo = (TextView) findViewById(R.id.tv_no);
        mTvNo.setOnClickListener(this);
        mBtnShow = (Button) findViewById(R.id.btn_show);
        mBtnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                submit();
                break;
            case R.id.btn_show:
                Intent intent = new Intent(this,SharedReadActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void submit() {
        // validate
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String psd = mEtPsd.getText().toString().trim();
        if (TextUtils.isEmpty(psd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        if (mCheckBoxPsd.isChecked()) {
            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
            mEditor.putString("User", name);
            mEditor.putString("Password", psd);
            mEditor.commit();
            Toast.makeText(this, "密码已保存", Toast.LENGTH_SHORT).show();
        }


    }
}
