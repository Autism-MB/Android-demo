package com.sziit.chapter3_4listview;

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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView mTextView;
    private ListView mListview;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.f, R.drawable.d,
            R.drawable.e, R.drawable.g};
    private String[] strName = {"挂柯南", "[禁言]19互联3-2班", "订阅号消息", "微信团队", "文件传输助手", "麦当劳", "19互联3-2班"};
    private String[] strtime = {"10.20", "10.21", "10.22", "10.23", "10.24", "10.25", "10.26"};
    private String[] strContent = {"肖茂彬做好了", "XX：xx通知", "新的信息", "登录操作通知", "你有新的文件", "[5G]来了，麦麦学生会", "肖茂彬已填"};
    private int ipic =R.drawable.mdr;
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
            map.put("ipic",ipic);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.list_item, new String[]{"image", "name", "time", "Content","ipic"},
                new int[]{R.id.img, R.id.tv1, R.id.tv2, R.id.tv3,R.id.imageView}
        );
        mListview.setAdapter(msimpleAdapter);

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,Object> map = mArrayList.get(position);
        String strMsg = String.format("肖茂彬选择了第%d个子选项: " +map.get("name"),position+ 1);
        Toast.makeText(this, strMsg, Toast.LENGTH_SHORT).show();

    }
}

