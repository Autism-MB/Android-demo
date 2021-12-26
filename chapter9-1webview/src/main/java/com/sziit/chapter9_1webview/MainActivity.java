package com.sziit.chapter9_1webview;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mButton;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initWebView("https://www.sina.com.cn/");
    }

    private void initWebView(String strUrl) {
        mWebview.getSettings().setJavaScriptEnabled(true);//支持javascript脚本
        mWebview.setWebViewClient(new WebViewClient());//设置网络客户端
        if (strUrl == null) {
            mWebview.loadUrl("https://www.sina.com.cn/");//设置网页的地址
        } else {
            mWebview.loadUrl(strUrl);
        }

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                submit();
                break;
        }
    }

    private void submit() {
        String editTextString = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(editTextString)){
            Toast.makeText(this,"editText不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        initWebView(editTextString);
    }

}
