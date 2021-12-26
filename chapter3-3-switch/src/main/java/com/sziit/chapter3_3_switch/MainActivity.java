package com.sziit.chapter3_3_switch;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView mTextView;
    private Switch mSwitch1;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mSwitch1 = (Switch) findViewById(R.id.switch1);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnCheckedChangeListener(this);
        mSwitch1.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton , boolean b) {
        String strMsg = "";
        if (b) {
            strMsg = String.format("控件:%s;ID:%d;被选中", compoundButton.getText(), compoundButton.getId());
        } else {
            strMsg = String.format("控件:%s;ID:%d;被取消", compoundButton.getText(), compoundButton.getId());
        }
        mTextView.setText(strMsg);
    }
}
