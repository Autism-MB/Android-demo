package com.sziit.chapter3_2middelview;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener {


    private CheckBox mCheckBox2;
    private Switch mSwitch1;
    private RadioGroup mRadioGroup3;
    private RadioGroup mRadiogroup4;
    private RadioButton mRadioButton;
    private RadioButton mRadioButton2;
    private RadioGroup mRadioGroup;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mCheckBox2.setOnCheckedChangeListener(this);
        mSwitch1 = (Switch) findViewById(R.id.switch1);
        mSwitch1.setOnCheckedChangeListener(this);
        mRadioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        mRadioGroup3.setOnCheckedChangeListener(this);
        mRadiogroup4 = (RadioGroup) findViewById(R.id.radiogroup4);
        mRadiogroup4.setOnCheckedChangeListener(this);
        mRadioButton = (RadioButton) findViewById(R.id.radioButton);
        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mRadioGroup3.check(R.id.radioButton);
        mRadioGroup3.check(R.id.radioButton2);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.timg);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String strMsg;
        if (isChecked) {
            strMsg = "肖茂彬1907040231控件：" + buttonView.getText() + "被勾选";
        } else {
            strMsg = "肖茂彬1907040231控件：" + buttonView.getText() + "取消勾选";
        }
        switch (buttonView.getId()) {
            case R.id.checkBox2:
                Toast.makeText(this, "CheckBox" + strMsg, Toast.LENGTH_LONG).show();
                break;
            case R.id.switch1:
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String strMsg = "";
        RadioButton mRadioButton;
        mRadioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (group.getId() == R.id.radioGroup3) {
            strMsg = "您的性别为：" + mRadioButton.getText().toString();
        }
        if (group.getId() == R.id.radiogroup4) {
            strMsg = "您的学历为：" + mRadioButton.getText().toString();
        }
        Toast.makeText(this, strMsg, Toast.LENGTH_SHORT).show();
        switch (checkedId){
            case R.id.radioButton3:
                mImageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
            case R.id.radioButton4:
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case R.id.radioButton5:
                mImageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case R.id.radioButton6:
                mImageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case R.id.radioButton7:
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case R.id.radioButton8:
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case R.id.radioButton9:
                mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case R.id.radioButton10:
                mImageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
        }
    }
}


