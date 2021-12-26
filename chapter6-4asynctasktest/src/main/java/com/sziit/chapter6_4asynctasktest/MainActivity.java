package com.sziit.chapter6_4asynctasktest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartAsync;
    private Button mBtnStartDownload;
    private Button mBtnStartDownload2;
    private Button mBtnDownloadStart;
    private Button mBtnDownloadPause;
    private Button mBtnDownloadCancel;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private DownloadBinder mDownloadBinder;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        inData();
    }

    private void inData() {
        EventBus.getDefault().register(this);
        mServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mDownloadBinder = (DownloadBinder) service;
            }
        };
    }

    private void initView() {
        mBtnStartAsync = (Button) findViewById(R.id.btn_start_async);
        mBtnStartDownload = (Button) findViewById(R.id.btn_start_download);
        mBtnStartDownload2 = (Button) findViewById(R.id.btn_start_download2);
        mBtnDownloadStart = (Button) findViewById(R.id.btn_download_start);
        mBtnDownloadPause = (Button) findViewById(R.id.btn_download_pause);
        mBtnDownloadCancel = (Button) findViewById(R.id.btn_download_cancel);
        mTextView = (TextView) findViewById(R.id.textView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBtnStartAsync.setOnClickListener(this);
        mBtnStartDownload.setOnClickListener(this);
        mBtnStartDownload2.setOnClickListener(this);
        mBtnDownloadStart.setOnClickListener(this);
        mBtnDownloadPause.setOnClickListener(this);
        mBtnDownloadCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_async:
                startAsync();
                break;
            case R.id.btn_start_download:
                startDownloadAsync();
                break;
            case R.id.btn_start_download2:
                Intent intent = new Intent(this, DownloadService.class);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_download_start:
                mDownloadBinder.startDownload("");
                break;
            case R.id.btn_download_pause:
                mDownloadBinder.puaseDownload();
                break;
            case R.id.btn_download_cancel:
                mDownloadBinder.cancelDownload();
                break;
        }
    }

    private void startDownloadAsync() {
        DownloadAsyncTask mDDownloadAsyncTask=new DownloadAsyncTask(this);
        mDDownloadAsyncTask.execute();
    }

    private void startAsync() {
        Log.d("MainActivity","startAsync");
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("AsyncTask test");
    }

    @Subscribe
    public void onEventMainThread(EventData event) {
        if (event == null) {
            return;
        }
        switch (event.getiType())
        {
            case 0:
                mTextView.setText("下载完成:"+"100%");
                break;
            case 1:
                mTextView.setText("下载失败:");
                break;
            case 2:
                mTextView.setText("下载暂停:");
                break;
            case 3:
                mTextView.setText("下载取消:");
                break;
            case 4:
                mTextView.setText("下载进度:" +event.getiProgress()+"%");
                mProgressBar.setProgress(event.getiProgress());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
