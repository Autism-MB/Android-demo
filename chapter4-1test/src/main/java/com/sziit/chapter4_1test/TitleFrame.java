package com.sziit.chapter4_1test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TitleFrame extends Fragment implements AdapterView.OnItemClickListener {

    private TextView mTextView;
    private ListView mListview;
    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.f, R.drawable.d,
            R.drawable.h, R.drawable.g};
    private String[] strName = {"挂柯南", "[禁言]19互联3-2班", "订阅号消息", "微信团队", "文件传输助手", "麦当劳", "19互联3-2班"};
    private String[] strtime = {"10.20", "10.21", "10.22", "10.23", "10.24", "10.25", "10.26"};
    private String[] strContent = {"肖茂彬做好了", "XX：xx通知", "新的信息", "登录操作通知", "你有新的文件", "[5G]来了，麦麦学生会", "肖茂彬已填"};
    private int ipic = R.drawable.mdr;
    private ArrayList<Map<String, Object>> mArrayList;
    //private SearchView mSearchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
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
            map.put("ipic", ipic);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                getActivity(), mArrayList, R.layout.list_item, new String[]{"image", "name", "time", "Content", "ipic"},
                new int[]{R.id.img, R.id.tv1, R.id.tv2, R.id.tv3, R.id.imageView}
        );
        mListview.setAdapter(msimpleAdapter);
    }

    private void initView(View view) {
        //mSearchView = (SearchView) view.findViewById(R.id.searchView);
        mListview = (ListView) view.findViewById(R.id.listview);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = mArrayList.get(position);
        switch (position) {
            case 0:
                setContent(strName[0], strContent[0]);
                break;
            case 1:
                setContent(strName[1], strContent[1]);
                break;
            case 2:
                setContent(strName[2], strContent[2]);
                break;
            case 3:
                setContent(strName[3], strContent[3]);
                break;
            case 4:
                setContent(strName[4], strContent[4]);
                break;
            case 5:
                setContent(strName[5], strContent[5]);
                break;
            case 6:
                setContent(strName[6], strContent[6]);
                break;
        }
    }

    private void setContent(String strTitle, String strContent) {
        ContentFragment mContentFragment = (ContentFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_content);
        mContentFragment.setData(strTitle, strContent);
    }

}
