package com.sziit.chapter4_1newslist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {
    private TextView mTxtTitle;
    private TextView mTxtContent;

    private String TAG="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        initView(view);
        TAG=getClass().toString();
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    private void initView(View view) {
        mTxtTitle = (TextView) view.findViewById(R.id.txt_title);
        mTxtContent = (TextView) view.findViewById(R.id.txt_content);
    }
    public void setData(String strTitle,String strContent)
    {
        mTxtTitle.setText(strTitle);
        mTxtContent.setText(strContent);
    }
}
