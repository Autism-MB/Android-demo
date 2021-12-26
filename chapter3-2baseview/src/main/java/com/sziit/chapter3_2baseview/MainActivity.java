package com.sziit.chapter3_2baseview;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private TextView mTextView8;
    private Button mBtnTest1;
    private Button mBtnText2;
    private EditText mEditText;
    private TextView mTvProgress;
    private ProgressBar mProgressBar;
    private Button mButton;
    private Button mButton2;
    private int iProgress = 0;//进度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTextView();
    }

    private void initTextView() {
        mTextView8.setText("湖北省除武汉市以外地区，将于3月25日起解除离鄂通道管控，武汉市将于4月8日起解除离汉离鄂通道管控措施。");
        mTextView8.setTextColor(Color.BLUE);
        mTextView8.setTextSize(20);
        mTextView8.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTextView8.setSingleLine();
        mTextView8.setSelected(true);
        mTextView8.setMarqueeRepeatLimit(-1);
    }

    private void initView() {
        mTextView8 = (TextView) findViewById(R.id.textView8);
        mBtnTest1 = (Button) findViewById(R.id.btn_test1);
        mBtnTest1.setOnClickListener(this);
        mBtnTest1.setOnLongClickListener(this);
        mBtnText2 = (Button) findViewById(R.id.btn_text2);
        mBtnText2.setOnClickListener(this);
        mBtnText2.setOnLongClickListener(this);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText.setOnClickListener(this);
        mTvProgress = (TextView) findViewById(R.id.tv_progress);
        mTvProgress.setOnClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setOnClickListener(this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strMsg;
        switch (v.getId()) {
            case R.id.btn_test1:
                strMsg = "肖茂彬+1907040231" + ((Button) v).getText().toString() + "被单击了";
                Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_text2:
                strMsg = "肖茂彬+1907040231" + ((Button) v).getText().toString() + "被长按了";
                Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
                break;
            case R.id.editText:
                submit();
                break;
            case R.id.button:
                handIncrease();
                break;
            case R.id.button2:
                autoIncrease();
                break;
        }
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
                            mTvProgress.setText("进度:" + iProgress + "%");
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

    private void handIncrease() {
        iProgress += 10;
        if (iProgress > 100) {
            iProgress = 0;
        }
        mProgressBar.setProgress(iProgress);
        mTvProgress.setText("进度:" + iProgress + "%");
    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }

    private void submit() {
        // validate
        String editTextString = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(editTextString)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        Toast.makeText(this, "    " + editTextString, Toast.LENGTH_SHORT).show();
    }
}
