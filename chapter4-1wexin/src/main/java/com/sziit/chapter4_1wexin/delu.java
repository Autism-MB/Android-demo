package com.sziit.chapter4_1wexin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delu extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText5;
    private Button mBtnPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delu);
        initView();
    }

    private void initView() {
        mEditText5 = (EditText) findViewById(R.id.editText5);
        mEditText5.setOnClickListener(this);
        mEditText5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        mBtnPass = (Button) findViewById(R.id.btn_pass);
        mBtnPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pass:
                String edittext = mEditText5.getText().toString().trim();
                if (edittext.equals("1907040231")) {
                    Toast.makeText(this, "欢迎回来！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "你输入的密码错误！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void finish() {
    }
}
