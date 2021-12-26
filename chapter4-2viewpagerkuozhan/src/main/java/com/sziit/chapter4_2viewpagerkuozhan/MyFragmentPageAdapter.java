package com.sziit.chapter4_2viewpagerkuozhan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mCtx;
    private ArrayList<Fragment> fragments;
    private List<String> mTitleList;

    public MyFragmentPageAdapter(FragmentManager fm ,Context mCtx, ArrayList<Fragment> fragments,List<String> mTitleList) {
        super(fm);
        this.mCtx=mCtx;
        this.fragments=fragments;
        this.mTitleList=mTitleList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}

