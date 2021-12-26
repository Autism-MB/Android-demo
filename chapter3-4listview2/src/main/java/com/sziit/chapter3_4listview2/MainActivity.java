package com.sziit.chapter3_4listview2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView mTextview;
    private ListView mLs;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c};
    private String[] strStress = {"深圳维也纳酒店", "深圳锦绣濠江酒店", "深圳帝豪酒店"};
    private String[] strfenshu = {"4.9分", "5.0分", "4.8分"};
    private String[] strluxiang = {"/22点评3室6房6人距你3.6km", "22点评3室6房6人距你3.6km", "122点评3室6房6人距你3.6km"};
    private String[] strmoney = {"￥820", "￥1000", "￥1500"};
    private String[] stryouhui = {"已减230", "已减230", "已减230"};
    private String[] strHello = {"仅限今日入住！", "仅限今日入住！", "仅限今日入住！"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("stress", strStress[i]);
            map.put("fenshu", strfenshu[i]);
            map.put("luxiang", strluxiang[i]);
            map.put("money", strmoney[i]);
            map.put("youhui", stryouhui[i]);
            map.put("hello", strHello[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.list_item, new String[]{"image", "stress", "fenshu", "luxiang","money","youhui","hello"},
                new int[]{R.id.iv, R.id.tv1, R.id.tv2, R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6}
        );
        mLs.setAdapter(msimpleAdapter);
    }

    private void initView() {
        mTextview = (TextView) findViewById(R.id.textview);
        mLs = (ListView) findViewById(R.id.ls);
        mLs.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = mArrayList.get(position);
        String strMsg = String.format("肖茂彬选择了第%d个子选项: " +map.get("stress"),position+ 1);
        Toast.makeText(this, strMsg, Toast.LENGTH_SHORT).show();
    }
}
