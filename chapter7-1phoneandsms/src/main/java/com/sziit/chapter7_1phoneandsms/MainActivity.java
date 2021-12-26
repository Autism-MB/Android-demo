package com.sziit.chapter7_1phoneandsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCall;
    private Button mBtnSms;
    private Button mBtnPermission;
    private static final int REQUEST_CODE_CALL_PHONE = 1;
    private static final int REQUEST_CODE_CALL_SES = 2;
    private static final int REQUEST_CODE_CALL_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnCall = (Button) findViewById(R.id.btn_call);

        mBtnCall.setOnClickListener(this);
        mBtnSms = (Button) findViewById(R.id.btn_sms);
        mBtnSms.setOnClickListener(this);
        mBtnPermission = (Button) findViewById(R.id.btn_permission);
        mBtnPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                call();
                break;
            case R.id.btn_sms:
                DealSms();
                break;
            case R.id.btn_permission:
                permission();
                break;
        }
    }
    private void call() {
        //checkSelfPermission()检查权限
        //判断应用是否有拨打电话权限
        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限的话,申请权限
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1
            );
        }else {
            //如果有权限的话可以直接调用拨打函数的权限
            makeCall();
        }
    }
    private void DealSms() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 2);
        } else {
            sendsms();
        }
    }
    private void permission(){
        ActivityCompat.requestPermissions(
                MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1
        );
    }
    //int requestCode 会在回调onRequestPermissionsResult()时返回,用来判断是哪个授权申请的回调
    //String[] permissions 权限数组,你需要申请的权限的数组
    //int[] grantResults 授权结果数组
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CALL_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendsms();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
                break;
            case REQUEST_CODE_CALL_SES:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"YOU DENY CALL_PHONE PERMTSSON",Toast.LENGTH_LONG).show();
                }
                if (grantResults.length>0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"YOU DENY CALL_PHONE PERMTSSON",Toast.LENGTH_LONG).show();
                }
            default:
                break;
        }
    }
    private void makeCall(){
        try {
            //建立意图
            Intent intent = new Intent(Intent.ACTION_CALL);
            //设置默认的参数
            intent.setData(Uri.parse("tel:10086"));
            //调用系统功能
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    private void sendsms() {
        try {
            Uri uri = Uri.parse("smsto:" + 10086);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}





