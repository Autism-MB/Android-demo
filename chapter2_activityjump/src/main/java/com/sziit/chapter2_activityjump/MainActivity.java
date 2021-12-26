package com.sziit.chapter2_activityjump;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG;
    private TextView mTextView1;
    private TextView mTextView2;
    private Button mBtnJump;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        if(savedInstanceState != null)
        {
            String strUser = savedInstanceState.getString("user");
            mEditText.setText(strUser);
        }
    }

    private void initData() {
        TAG = getLocalClassName().toString();
        Log.d(TAG, "onCreate: ");

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("user", mEditText.getText().toString());
    }

    private void initView() {
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mBtnJump = (Button) findViewById(R.id.btn_jump);
        mBtnJump.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                jumpToSecondActivity();
                break;
        }
    }

    public static final int REQUEST_CODE = 1;
    private void jumpToSecondActivity() {
        String strData = "admin";
        Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
        mIntent.putExtra("user", strData);
        startActivityForResult(mIntent, REQUEST_CODE);
    }

    private String strReturnData;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    strReturnData = data.getStringExtra("password");
                    mTextView2.setText("第二个Activity传回的password：" + strReturnData);
                }
                break;
            default:

        }
    }

}

