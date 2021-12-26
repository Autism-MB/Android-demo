package com.sziit.chapter3_4listview4;

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

    private TextView mTextView;
    private ListView mLs;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,};
    private String[] strName = {"整租·一米单间旅馆·各种单间", "整租·一米单间旅馆·各种双人房", "整租·一米单间旅馆·豪华单间", "整租·一米单间旅馆·豪华双人房"};
    private String[] strtime = {"独立厕所/有窗/干净/阳光", "独立厕所/有窗/干净/阳光", "独立厕所/有窗/干净/阳光", "独立厕所/有窗/干净/阳光"};
    private String[] strContent = {"龙岗区大运新城 据3公里（龙岗线）", "龙岗区大运新城 据3公里（龙岗线）", "龙岗区大运新城 据3公里（龙岗线）", "龙岗区大运新城 据3公里（龙岗线）"};
    private String[] strMoney = {"1400/月","2000/月","1800/月","1600/月"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        inData();
    }

    private void inData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name", strName[i]);
            map.put("time", strtime[i]);
            map.put("Content", strContent[i]);
            map.put("money",strMoney[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.list_item, new String[]{"image", "name", "time", "Content","money"},
                new int[]{R.id.imageView2, R.id.tv_1, R.id.tv_2, R.id.tv_3,R.id.tv_7}
        );
        mLs.setAdapter(msimpleAdapter);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mLs = (ListView) findViewById(R.id.ls);
        mLs.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = mArrayList.get(position);
        String strMsg = String.format("肖茂彬选择了第%d个子选项: " +map.get("name"),position+ 1);
        Toast.makeText(this, strMsg, Toast.LENGTH_SHORT).show();
    }
}
