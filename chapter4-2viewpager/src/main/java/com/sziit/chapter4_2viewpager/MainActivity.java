package com.sziit.chapter4_2viewpager;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RadioGroup;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager mViewpager;
    private ArrayList<ImageFrame> fragments;
    private ImageFragmentPageAdapter imageFragmentPageAdapter;
    private PagerTabStrip mPagerTabStrip;
    private int[] iImage = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
    private List<Integer> mTabs ;
    private RadioGroup mRadiogroup;
    private List<String> mTitleList;
    private String[] strTitles={"彩霞","礼堂","广场","落日","红日","篮球场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < iImage.length; i++) {
            ImageFrame imageFrame = new ImageFrame();
            imageFrame.setImageView(iImage[i]);
            fragments.add(imageFrame);
        }
        //RadioButtonid初始化
        mTabs = new ArrayList<>();
        mTabs.add(R.id.radioButton);
        mTabs.add(R.id.radioButton2);
        mTabs.add(R.id.radioButton3);
        mTabs.add(R.id.radioButton4);
        mTabs.add(R.id.radioButton5);
        mTabs.add(R.id.radioButton6);
        mRadiogroup.check(R.id.radioButton);

        //title初始化
        mTitleList= new ArrayList<>();
        for(int i=0;i<strTitles.length;i++){
            mTitleList.add(strTitles[i]);
        }
        //设置选项卡属性
        mPagerTabStrip.setTextColor(Color.RED);/*设置文本颜色*/
        mPagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);/*设置文本颜色*/
        mPagerTabStrip.setDrawFullUnderline(true);//长线
        mPagerTabStrip.setTabIndicatorColor(Color.RED);/*设置短线颜色*/
        mPagerTabStrip.setTextSpacing(0);

        imageFragmentPageAdapter = new ImageFragmentPageAdapter(getSupportFragmentManager(), this, fragments,mTitleList);
        mViewpager.setAdapter(imageFragmentPageAdapter);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mViewpager.addOnPageChangeListener(this);
        mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        mRadiogroup.setOnCheckedChangeListener(this);
        mPagerTabStrip=(PagerTabStrip)findViewById(R.id.pagertabstrip);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mViewpager.setCurrentItem(mTabs.indexOf(checkedId));//根据RadioButton选中ID确定ViewPager的显示序号
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //根据ViewPager选中序号确定RadioButton的选中Button
        mRadiogroup.check(mTabs.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
