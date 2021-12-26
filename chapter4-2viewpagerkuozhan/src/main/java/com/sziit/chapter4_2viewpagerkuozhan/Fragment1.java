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

public class Fragment1 extends Fragment implements AdapterView.OnItemClickListener{

    private ListView mLsGuanzhu;
    private String[] iname = {"媳妇进门25年,从不肯叫婆婆一声妈,徐磊：那叫爸爸","48岁妻子长相奇异,还嫌弃丈夫学历低,徐磊好奇:你啥学历",
            "父母抢红包欠下巨额债务,在家中服药自杀的儿子亲自调查揭开秘密"};
    private int[] iPic = {R.drawable.ganzhu_a, R.drawable.ganzhu_b, R.drawable.ganzhu_c};
    private ArrayList<Map<String, Object>> mArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mLsGuanzhu=(ListView) view.findViewById(R.id.ls_guanzhu);
        mLsGuanzhu.setOnItemClickListener(this);
    }
    private void initData(){
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", iPic[i]);
            map.put("name",iname[i]);
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                getActivity(), mArrayList, R.layout.ls_guazhu, new String[]{"image","name"},
                new int[]{R.id.imageView,R.id.textView9}
        );
        mLsGuanzhu.setAdapter(msimpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
