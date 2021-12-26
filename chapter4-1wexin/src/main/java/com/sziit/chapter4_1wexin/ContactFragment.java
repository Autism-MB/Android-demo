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

public class ContactFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mLsCantact;
    private int[] iPic = {R.drawable.contact_a, R.drawable.contact_b, R.drawable.contact_c, R.drawable.contact_d, R.drawable.contact_e,
            R.drawable.contact_f,R.drawable.contact_g};
    private String[] strName = {"新的朋友", "群聊", "标签", "公众号", "A03大白", "Autism","吖"};
    private ArrayList<Map<String, Object>> mArrayList;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framelayout_contact, container, false);
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
            mArrayList.add(map);
        }
        SimpleAdapter msimpleAdapter;
        msimpleAdapter = new SimpleAdapter(
                getActivity(), mArrayList, R.layout.list_cantact, new String[]{"image", "name"},
                new int[]{R.id.img_cat, R.id.ts_cantact}
        );
        mLsCantact.setAdapter(msimpleAdapter);
    }

    private void initView(View view) {
        mLsCantact = (ListView) view.findViewById(R.id.ls_cantact);
        mLsCantact.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent = new Intent(ContactFragment.this.getActivity(), newfriend.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void finish() {
    }
}
