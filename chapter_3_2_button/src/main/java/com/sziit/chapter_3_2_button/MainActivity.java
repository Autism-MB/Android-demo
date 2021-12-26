package com.sziit.chapter_3_2_button;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {
    private Button mBtnTest1;
    private Button mBtnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnTest1 = (Button) findViewById(R.id.btn_test1);
        mBtnTest1.setOnClickListener(this);
        mBtnTest1.setOnLongClickListener(this);
        mBtnTest2 = (Button) findViewById(R.id.btn_test2);
        mBtnTest2.setOnClickListener(this);
        mBtnTest2.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                Toast.makeText(this, "按钮" + ((Button) v).getText().toString() + "被单击了",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test2:
                Toast.makeText(this, "按钮" + ((Button) v).getText().toString() + "被单击了",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                Toast.makeText(this, "按钮" + ((Button) v).getText().toString() + "被长按了",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test2:
                Toast.makeText(this, "按钮" + ((Button) v).getText().toString() + "被长按了",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
