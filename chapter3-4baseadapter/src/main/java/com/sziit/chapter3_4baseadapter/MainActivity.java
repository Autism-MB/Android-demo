package com.sziit.chapter3_4baseadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int[] iPic = {R.drawable.a, R.drawable.z, R.drawable.p, R.drawable.e, R.drawable.f, R.drawable.g};
    private String[] strName = {"深圳维也纳酒店", "深圳锦绣濠江酒店", "深圳帝豪酒店", "深圳豪逸酒店", "深圳和美酒店", "深圳7日酒店"};
    private int[] iContent = {4,5,3,8,7,9};
    private int[] iMoney = {894, 458, 785, 448, 955, 255, 664};
    private int[] iinfo ={451,88,156,436,111,345};
    private TextView mTextView;
    private ListView mListview;
    private ArrayList<ItemBean> mArrayList;
    private MyBaseAdapter myBaseAdapter;
    private Spinner mSpinner;
    private String[] strsort ={"请选择排序方式","评分从高到低","评分从低到高","价钱从高到低","价钱从低到高","折扣从高到低","折扣从低到高"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //ArrayAdapter<String> mArrayAdapter =
                new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,strsort);
       // mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setPrompt("请选择排序方式");
        mArrayList = new ArrayList<>();
        for (int i = 0; i < iPic.length; i++) {
            ItemBean mItemBean = new ItemBean();
            mItemBean.setiPic(iPic[i]);
            mItemBean.setStrName(strName[i]);
            mItemBean.setiContent(iContent[i]);
            mItemBean.setiMoney(iMoney[i]);
            mItemBean.setIifo(iinfo[i]);
            mArrayList.add(mItemBean);
        }
        Collections.sort(mArrayList,TravelComparator.ScoreDeseending);
        myBaseAdapter = new MyBaseAdapter(this, mArrayList);
        mListview.setAdapter(myBaseAdapter);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mListview = (ListView) findViewById(R.id.listview);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                Collections.sort(mArrayList,TravelComparator.f1);
                myBaseAdapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(mArrayList,TravelComparator.f2);
                myBaseAdapter.notifyDataSetChanged();
                break;
            case 3:
                Collections.sort(mArrayList,TravelComparator.ScoreDeseending);
                myBaseAdapter.notifyDataSetChanged();
                break;
            case 4:
                Collections.sort(mArrayList,TravelComparator.AnDeseding);
                myBaseAdapter.notifyDataSetChanged();
                break;
            case 5:
                Collections.sort(mArrayList,TravelComparator.z1);
                myBaseAdapter.notifyDataSetChanged();
                break;
            case 6:
                Collections.sort(mArrayList,TravelComparator.z2);
                myBaseAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
