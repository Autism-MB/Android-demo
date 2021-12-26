package com.sziit.chapter_3_2_progressbar;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAuto;
    private ProgressBar mProgressBar;
    private Button mBtnHand;
    private TextView mTextView;
    private int iProgress = 0;//进度
    private ProgressBar mProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnAuto = (Button) findViewById(R.id.btn_auto);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mBtnHand = (Button) findViewById(R.id.btn_hand);
        mBtnAuto.setOnClickListener(this);
        mBtnHand.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnClickListener(this);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressBar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_auto:
                autoIncrease();
                break;
            case R.id.btn_hand:
                handIncrease();
                break;
        }
    }

    private void handIncrease() {
        iProgress += 10;
        if (iProgress >= 100) {
            iProgress = 0;
        }
        mProgressBar.setProgress(iProgress);
        mTextView.setText("进度:" + iProgress + "%");
    }

    private void autoIncrease() {
        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (iProgress <= 100) {
                    iProgress += 10;
                    if (iProgress > 100) {
                        iProgress = 0;
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(iProgress);
                            mTextView.setText("进度:" + iProgress + "%");
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.start();
    }
}