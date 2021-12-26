package com.sziit.chapter4_1wexin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class second extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        initView();
    }

    private void initView() {
        imageView14 = (ImageView) findViewById(R.id.imageView14);
        imageView14.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView14:
                Intent intent = new Intent(this,delu.class);
                startActivity(intent);
                finish();
        }
    }
    public void finish() {
    }
}
