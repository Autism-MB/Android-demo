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

public class MeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mLsMe;
    private int[] iPic = {R.drawable.me_a, R.drawable.me_b, R.drawable.me_c,R.drawable.me_d,R.drawable.me_f};
    private String[] iname = {"支付","收藏","相册","卡包","设置"};
    private String[] ifu = {">",">",">",">",">",">"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framelayout_me, container, false);
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
                getActivity(), mArrayList, R.layout.list_me, new String[]{"image","name","fu"},
                new int[]{R.id.imageView12,R.id.textView5,R.id.textView6}
        );
        mLsMe.setAdapter(msimpleAdapter);
    }
    private void initView(View view) {
        mLsMe = (ListView) view.findViewById(R.id.ls_me);
        mLsMe.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 4:
                Intent intent = new Intent(MeFragment.this.getActivity(), xx.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void finish() {
    }
}
