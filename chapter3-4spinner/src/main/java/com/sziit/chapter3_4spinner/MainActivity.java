package com.sziit.chapter3_4spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView mTextView;
    private Spinner mSpinner;
    private String[] strWeek={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initData() {
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>
                (this,R.layout.support_simple_spinner_dropdown_item,strWeek);
        mSpinner.setPrompt("请选择日期");
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setSelection(0);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String strMsg = "选择了第"+position+"个选项："+strWeek[position];
        Toast.makeText(this,strMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
