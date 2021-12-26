package com.sziit.chapter2_jump;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtn =(Button) findViewById(R.id.button);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                jumpToSecondActivity();
                break;
             default:
                break;
        }
    }

    private void jumpToSecondActivity() {
        Intent mintent = new Intent(this,SecondActivity.class);
        startActivity(mintent);
    }
}
