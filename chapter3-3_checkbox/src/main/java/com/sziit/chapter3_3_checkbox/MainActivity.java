package com.sziit.chapter3_3_checkbox;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView mTextView2;
    private CheckBox mCheckBox;
    private CheckBox mCheckBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mCheckBox.setOnCheckedChangeListener(this);
        mCheckBox2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        CheckBox mCheckBox = (CheckBox) compoundButton;
        String strMsg="";
        if (b){
            strMsg = String.format("控件：%s;ID:%d;被选中",mCheckBox.getText(),mCheckBox.getId());
        }else {
            strMsg = String.format("控件：%s;ID:%d;被取消",mCheckBox.getText(),mCheckBox.getId());
        }
        mTextView2.setText(strMsg);
    }
}
