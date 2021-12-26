package com.sziit.chapter9_okhttptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpThread extends Thread {
    private HttpListener mHttpListener;
    private String strUrl;
    public HttpThread(String strUrl,HttpListener mHttpListener)
    {
        this.strUrl=strUrl;
        this.mHttpListener=mHttpListener;
    }
    @Override
    public void run() {
        super.run();
        //打开http链接
        HttpURLConnection mHttpURLConnection = null;//新建HttpURLConnection访问对象
        BufferedReader mBufferedReader = null;//新建BufferedReader号称
        InputStream in=null;//新建InputStream输入流
        try {
            URL mUrl = new URL(strUrl);//新建URL对象
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();//根据URL初始化HttpURLConnection访问对象
            //设置超时时间
            mHttpURLConnection.setConnectTimeout(5000);
            //指定请求方式为GET方式
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setReadTimeout(5000);
            //不用再去判断状态码是否为200
            in= mHttpURLConnection.getInputStream();//获取输入流
            mBufferedReader = new BufferedReader(new InputStreamReader(in));//将输入流放入缓存
            StringBuilder response = new StringBuilder();//新建字符串缓存
            String line;
            //按行读取输入流缓存并将网络访问所有请求存入字符串缓存
            while ((line = mBufferedReader.readLine()) != null) {
                response.append(line);
            }
            //网络请求成功将信息提交给回调接口
            mHttpListener.onSuccess(response.toString());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            //网络请求失败将信息提交给回调接口
            mHttpListener.onFailed(e.toString());
        }finally {
            if (mBufferedReader!=null)
            {
                try {
                    mBufferedReader.close();//关闭缓存
                    in.close();//关闭输入流
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                if(mHttpURLConnection!=null)
                {
                    mHttpURLConnection.disconnect();//关闭网络连接
                }
            }
        }

    }
}
