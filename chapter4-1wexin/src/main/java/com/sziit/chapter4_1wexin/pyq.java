package com.sziit.chapter4_1wexin;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pyq extends AppCompatActivity implements AdapterView.OnItemClickListener ,View.OnClickListener {

    private ListView mLsPyq;
    private int[] iPic = {R.drawable.xsjun, R.drawable.yht};
    private String[] iname = {"学生菌", "益禾堂"};
    private String[] ifu = {"菌菌被人恶意举报了,今天刚解封，消息晚点回", "益禾堂开始营业啦,欢迎小程序提前下单！"};
    private int[] itupian = {R.drawable.pyq_a, R.drawable.pyq_b};
    private String[] itime = {"44分钟前", "55分钟前"};
    private ArrayList<Map<String, Object>> mArrayList;
    private ScrollView mScpyq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_pyq);
        initView();
        initData();
        setListViewHeightBasedOnChildren(mLsPyq);
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name", iname[i]);
            map.put("fu", ifu[i]);
            map.put("tupian", itupian[i]);
            map.put("time", itime[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                this, mArrayList, R.layout.ls_pyq, new String[]{"image", "name", "fu", "tupian", "time"},
                new int[]{R.id.imageView8, R.id.textView21, R.id.textView22, R.id.imageView10, R.id.textView23}
        );
        mLsPyq.setAdapter(msimpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void initView() {
        mLsPyq = (ListView) findViewById(R.id.ls_pyq);
        mLsPyq.setOnItemClickListener(this);
        mScpyq = (ScrollView) findViewById(R.id.scpyq);
        mScpyq.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                finish();
                break;
        }
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
