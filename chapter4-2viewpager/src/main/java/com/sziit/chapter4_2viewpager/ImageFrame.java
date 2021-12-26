package com.sziit.chapter4_2viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageFrame extends Fragment {
    private ImageView imageView;
    private int iResId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.activity_main_frame,container,false);
        imageView=view.findViewById(R.id.imageView);
        imageView.setImageResource(iResId);
        return view;
    }

    public void setImageView(int resID){
        iResId = resID;
    }
}
