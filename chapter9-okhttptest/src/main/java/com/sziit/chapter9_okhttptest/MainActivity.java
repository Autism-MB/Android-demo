package com.sziit.chapter9_okhttptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextview;
    private EditText mEditIpadress;
    private Button mBtnHttp;
    private TextView mTextviewContent;
    private HttpListener mHttpListener;//新建网络监听器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mHttpListener=new HttpListener() {//初始化网络监听器对象
            @Override
            //实现onSuccess接口
            public void onSuccess(final String strResponse) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextviewContent.setText(strResponse); //将网络成功后获取数据显示
                    }
                });
            }


            @Override
            //实现onFailed接口
            public void onFailed(final String strResponse) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextviewContent.setText(strResponse);//将网络失败后获取数据显示
                    }
                });

            }
        };
    }

    private void initView() {
        mTextview = (TextView) findViewById(R.id.textview);
        mEditIpadress = (EditText) findViewById(R.id.edit_ipadress);
        mBtnHttp = (Button) findViewById(R.id.btn_http);
        mTextviewContent = (TextView) findViewById(R.id.textview_content);

        mBtnHttp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_http:
                submit();

                break;
        }
    }

    private void submit() {
        // validate
        String ipadress = mEditIpadress.getText().toString().trim();
        if (TextUtils.isEmpty(ipadress)) {
            Toast.makeText(this, "ip地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTextviewContent.setText("");
        //主界面启动Http线程
        HttpThread mHttpThread=new HttpThread(ipadress,mHttpListener);
        mHttpThread.start();



    }
}
