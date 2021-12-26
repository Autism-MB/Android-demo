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

public class FindFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView mLsFind;
    private int[] iPic = {R.drawable.find_a, R.drawable.find_b, R.drawable.find_c,R.drawable.find_d,R.drawable.find_e};
    private String[] iname={"朋友圈","视频号","扫一扫","看一看","小程序"};
    private String[] ifu={">",">",">",">",">"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framelayout_find, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name",iname[i]);
            map.put("fu",ifu[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                getActivity(), mArrayList, R.layout.list_find, new String[]{"image","name","fu"},
                new int[]{R.id.imageView9,R.id.textView,R.id.textView3}
        );
        mLsFind.setAdapter(msimpleAdapter);
    }
    private void initView(View view) {
        mLsFind = (ListView) view.findViewById(R.id.ls_find);
        mLsFind.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent = new Intent(FindFragment.this.getActivity(), pyq.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void finish() {
    }
}
