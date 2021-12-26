package com.sziit.chapter5_smsreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* 短信广播接收器 */
    private SmsReceiver mSmsReceiver;
    private TextView mTextView;
    private ListView mLvSms;

    private List<String> mListSmsDatas;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getSmsPermission();
        initData();
    }

    private void getSmsPermission() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    1);
        }//动态申请权限

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    1);
        }
    }

    private void initData() {
        EventBus.getDefault().register(this);
        mListSmsDatas = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListSmsDatas);
        mLvSms.setAdapter(mArrayAdapter);
        //设置短信广播接收的Action
        IntentFilter filter = new IntentFilter();
        filter.addAction(mSmsReceiver.SMS_RECEIVED_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        //注册广播
        super.registerReceiver(mSmsReceiver, filter);

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mLvSms = (ListView) findViewById(R.id.lv_sms);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this,
                            "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Subscribe
    public void onEventMainThread(EventData event) {
        if (event == null) {
            return;
        }
        if (event.getEventCode() == 1) {
            mListSmsDatas.add(event.getStrMsg());
            mArrayAdapter.notifyDataSetChanged();
        }

    }
}
