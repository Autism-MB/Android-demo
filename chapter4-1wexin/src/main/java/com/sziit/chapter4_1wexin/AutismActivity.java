package com.sziit.chapter4_1wexin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AutismActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Button mButton5;
    private ImageButton mBtnHear;
    private Button mButton4;
    private EditText mEditText2;
    private ListView mLsAutism;
    private ScrollView mScrollView2;
    private String[] iname = {"11:25", "12:50", "15：25", "15:48", "15:57", "16:44"};
    private String[] ifu = {"今天刚解封，消息晚点回", "欢迎小程序提前下单！", "记得写PHP作业", "晚上拿快递", "晚上打比赛", "认真学习"};
    private ArrayList<Map<String, Object>> mArrayList;
    private ImageButton mBtnXiao;
    private ImageButton mBtnJia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autism_me);
        initView();
        submit();
        initData();
        setListViewHeightBasedOnChildren(mLsAutism);
    }
    private void initView() {
        mButton5 = (Button) findViewById(R.id.button5);
        mBtnHear = (ImageButton) findViewById(R.id.btn_hear);
        mBtnJia= (ImageButton) findViewById(R.id.btn_jia);
        mButton4=(Button)findViewById(R.id.button4);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mLsAutism = (ListView) findViewById(R.id.ls_autism);
        mScrollView2 = (ScrollView) findViewById(R.id.scrollView2);
        mBtnXiao = (ImageButton) findViewById(R.id.btn_xiao);
        mBtnJia = (ImageButton) findViewById(R.id.btn_jia);
        mButton5.setOnClickListener(this);
        mBtnXiao.setOnClickListener(this);
        mBtnHear.setOnClickListener(this);
        mBtnJia.setOnClickListener(this);
        mBtnXiao.setOnClickListener(this);
        mBtnXiao.setOnClickListener(this);
        mBtnJia.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iname.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", iname[i]);
            map.put("fu", ifu[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.ls_autism, new String[]{"name", "fu"},
                new int[]{R.id.textView24, R.id.textView25}
        );
        mLsAutism.setAdapter(msimpleAdapter);
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    private void submit() {
        // validate
        String editText2String = mEditText2.getText().toString().trim();
        if (TextUtils.isEmpty(editText2String)) {
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
