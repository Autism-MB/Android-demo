package com.sziit.chapter4_1wexin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeXinFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListview;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f};
    private String[] strName = {"Autism", "男公关富婆多人运动裸聊群", "雨课堂", "几个弟弟", "订阅号信息", "高铁管家"};
    private String[] strtime = {"10.20", "10.21", "10.22", "10.23", "10.24", "10.25", "10.26"};
    private String[] strContent = {"[图片]", "肖茂彬做好了", "作业提交提醒", "我：刷抖音了刷抖音了", "您有24条新消息", "最新：高铁全面取消纸质车票！G/D/C字头列车"};
    private ArrayList<Map<String, Object>> mArrayList;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.framelayout_wexin,container,false);
        initView(view);
        initData();
        return view;
    }
    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name", strName[i]);
            map.put("time", strtime[i]);
            map.put("Content", strContent[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                getActivity(), mArrayList, R.layout.list_item, new String[]{"image", "name", "time", "Content"},
                new int[]{R.id.img, R.id.ts_name, R.id.ts_time, R.id.ts_content}
        );
        mListview.setAdapter(msimpleAdapter);
    }

    private void initView(View view) {
        mListview = (ListView) view.findViewById(R.id.listView);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent = new Intent(WeXinFragment.this.getActivity(), AutismActivity.class);
                startActivity(intent);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(WeXinFragment.this.getActivity(), wexin_qunliao.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    private void finish() {
    }
}
