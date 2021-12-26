package com.sziit.chapter4_2viewpagerkuozhan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment2 extends Fragment implements AdapterView.OnItemClickListener {


    private int[] iPic = {R.drawable.a, R.drawable.b, R.drawable.c};
    private String[] iname = {"影视:美女骑车装饰上富婆的宝马,却不知富婆是未来婆婆,结局真逗","教师夫妻吵架都这么文明,相互吐槽停不下来,徐磊听的哈哈大笑",
            "34岁女子没文化一个字都不认识,上场把所有人逗笑,连徐磊都笑懵"};
    private String[] ifu = {"新手妈妈来喊到  616评论  1个月前","去你的大冬天  551评论  1个月前","小鹿芭比5578  205评论  1周前"};
    private ArrayList<Map<String, Object>> mArrayList;
    private ListView mLsTuejian;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mLsTuejian = (ListView) view.findViewById(R.id.ls_tuejian);
        mLsTuejian.setOnItemClickListener(this);
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
                getActivity(), mArrayList, R.layout.ls_tuejian, new String[]{"image","name","fu"},
                new int[]{R.id.imageView,R.id.textView7,R.id.textView8}
        );
        mLsTuejian.setAdapter(msimpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
