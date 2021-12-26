package com.sziit.chapter4_1wexin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class newfriend extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private int[] iPic = {R.drawable.a};
    private String[] strName = {"Autism"};
    private String[] strfu ={"Hi 认识一下"};
    private String[] ck = {"查看"};
    private ArrayList<Map<String, Object>> mArrayList;
    private ListView mLsNewfriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_nefriends);
        initView();
        initData();
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name", strName[i]);
            map.put("fu",strfu[i]);
            map.put("ck",ck[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.ls_newfriends, new String[]{"image", "name","fu","ck"},
                new int[]{R.id.imageView13, R.id.textView11,R.id.textView12,R.id.textView13}
        );
        mLsNewfriends.setAdapter(msimpleAdapter);
    }

    private void initView() {
        mLsNewfriends = (ListView) findViewById(R.id.ls_newfriends);
        mLsNewfriends.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
