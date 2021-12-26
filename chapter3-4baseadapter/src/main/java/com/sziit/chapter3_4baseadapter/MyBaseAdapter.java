package com.sziit.chapter3_4baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<ItemBean> mArrayList;

    public MyBaseAdapter(Context mCtx, ArrayList<ItemBean> mArrayList) {
        this.mCtx = mCtx;
        this.mArrayList = mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView;
        ViewHolder mViewHolder;
        if (convertView == null) {
            mView = LayoutInflater.from(mCtx).inflate(R.layout.list_item, null);
            mViewHolder = new ViewHolder(mView);
            mView.setTag(mViewHolder);
        }else {
            mView = convertView;
            mViewHolder=(ViewHolder)mView.getTag();
        }
        ItemBean mItemBean = mArrayList.get(position);
        mViewHolder.mImageView2.setImageResource(mItemBean.getiPic());
        mViewHolder.mTextView2.setText(mItemBean.getStrName());
        mViewHolder.mTextView3.setText(mItemBean.getiContent()+".9分");
        mViewHolder.mTextView10.setText("￥"+mItemBean.getiMoney()+"元");
        mViewHolder.mTextView11.setText("￥"+mItemBean.getIifo());
        return mView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView mImageView2;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView10;
        public TextView mTextView11;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mImageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
            this.mTextView2 = (TextView) rootView.findViewById(R.id.textView2);
            this.mTextView3 = (TextView) rootView.findViewById(R.id.textView3);
            this.mTextView10 = (TextView) rootView.findViewById(R.id.textView10);
            this.mTextView11 = (TextView) rootView.findViewById(R.id.textView11);
        }

    }
}
