package com.sziit.chapter3_4listview3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView mTextView;
    private ListView mListview;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.f, R.drawable.d,
            R.drawable.e, R.drawable.g};
    private String[] strName = {"隆江猪脚饭", "咕咕店韩国炸鸡", "黄焖鸡米饭", "肯德基", "林家酸菜鱼", "七月上", "汕头草原牛肉火锅"};
    private String[] strtime = {"⭐4.9 月售3000", "⭐4.8 月售5000", "⭐4.7 月售4000", "⭐4.9 月售3000", "⭐4.9 月售3000", "⭐4.9 月售3000", "⭐4.9 月售3000"};
    private String[] strContent = {"40分钟 1km", "30分钟 0.75km", "40分钟 0.65km", "50分钟 1.5km", "40分钟 1km", "40分钟 1km", "40分钟 1km"};
    private String[] strConment = {"起送￥25 免费配送 人均￥35", "起送￥28 免费配送 人均￥55", "起送￥45 免费配送 人均￥45", "起送￥75 免费配送 人均￥45", "起送￥45 免费配送 人均￥35", "起送￥25 免费配送 人均￥35", "起送￥25 免费配送 人均￥35"};
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
            map.put("name", strName[i]);
            map.put("time", strtime[i]);
            map.put("Content", strContent[i]);
            map.put("Conment",strConment[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.ls_item, new String[]{"image", "name", "time", "Content","Conment"},
                new int[]{R.id.imageView, R.id.tv1, R.id.tv2, R.id.tv3,R.id.tv4}
        );
        mListview.setAdapter(msimpleAdapter);

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv1);
        mListview = (ListView) findViewById(R.id.ls);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = mArrayList.get(position);
        String strMsg = String.format("肖茂彬选择了第%d个子选项: " +map.get("name"),position+ 1);
        Toast.makeText(this, strMsg, Toast.LENGTH_SHORT).show();

    }
}

