package com.sziit.chapter4_1wexin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class xx extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mLsSz;
    private String[] iname={"账号与安全","新消息提醒","勿扰模式","聊天","隐私","通用","关于微信","帮助与反馈","插件"};
    private String[] ifu={">",">",">",">",">",">",">",">",">"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_xx);
        initView();
        initData();
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iname.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name",iname[i]);
            map.put("fu",ifu[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.ls_sz, new String[]{"name","fu"},
                new int[]{R.id.textView17,R.id.textView18}
        );
        mLsSz.setAdapter(msimpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void initView() {
        mLsSz = (ListView) findViewById(R.id.ls_sz);
        mLsSz.setOnItemClickListener(this);
    }
}
