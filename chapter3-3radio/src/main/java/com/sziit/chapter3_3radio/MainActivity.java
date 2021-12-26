package com.sziit.chapter3_3radio;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.sziit.chapter3_3radio.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView mTextView;
    private TextView mTextView2;
    private TextView mTextView3;
    private RadioButton mRadioButton;
    private RadioButton mRadioButton2;
    private RadioGroup mRadioGroup1;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private RadioButton mRadioButton6;
    private RadioGroup mRadioGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mRadioButton = (RadioButton) findViewById(R.id.radioButton);
        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mRadioGroup1 = (RadioGroup) findViewById(R.id.RadioGroup1);
        mRadioGroup1.setOnCheckedChangeListener(this);
        mRadioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        mRadioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        mRadioButton6 = (RadioButton) findViewById(R.id.radioButton6);
        mRadioGroup2 = (RadioGroup) findViewById(R.id.RadioGroup2);
        mRadioGroup2.setOnCheckedChangeListener(this);
        mRadioGroup2.check(R.id.radioButton6);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String strMsg = "";
        RadioButton mRadioButton;
        mRadioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (group.getId() == R.id.RadioGroup1)
        {
            strMsg="请选择性别为："+mRadioButton.getText().toString();
        }
        if (group.getId() == R.id.RadioGroup2)
        {
            strMsg="请选择学历为："+mRadioButton.getText().toString();
        }
        Toast.makeText(this,strMsg,Toast.LENGTH_SHORT).show();
    }
}
