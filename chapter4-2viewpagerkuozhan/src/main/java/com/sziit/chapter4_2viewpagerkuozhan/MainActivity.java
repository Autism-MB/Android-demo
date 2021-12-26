package com.sziit.chapter4_2viewpagerkuozhan;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<Fragment> mFragments;
    private ViewPager mViewpager;
    private PagerTabStrip mPagertabstrip;
    private MyFragmentPageAdapter adapter;//使用自定义FragmentPagerAdapter
    private List<String> mTitleList;
    private String[] strTitles={"关注","推荐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
    }
    private void initData(){
        //Fragments初始化
        mFragments = new ArrayList<>();
        mFragments.add(new Fragment1());
        mFragments.add(new Fragment2());
        //title初始化
        mTitleList= new ArrayList<>();
        for(int i=0;i<strTitles.length;i++){
            mTitleList.add(strTitles[i]);
        }
    }
    private void initAdapter() {
        mPagertabstrip.setTextColor(Color.RED);/*设置文本颜色*/
        mPagertabstrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);/*设置文本颜色*/
        //mPagertabstrip.setDrawFullUnderline(true);//长线
        mPagertabstrip.setTabIndicatorColor(Color.RED);/*设置短线颜色*/
        mPagertabstrip.setTextSpacing(0);
        //使用自定义FragmentPagerAdapter
        adapter = new MyFragmentPageAdapter(getSupportFragmentManager(),this,mFragments,mTitleList);
        mViewpager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mViewpager.addOnPageChangeListener(this);
        mPagertabstrip=(PagerTabStrip)findViewById(R.id.pagertabstrip);
        mPagertabstrip = (PagerTabStrip) findViewById(R.id.pagertabstrip);
    }
}
