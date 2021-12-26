package com.sziit.chapter9_jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView mTextview;
    private TextView mTextviewToday;
    private TextView mTextview1;
    private TextView mTextviewWeek;
    private Button mBtnFresh;
    private com.android.volley.Response.Listener mVolleyResponseListener;
    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mVolleyResponseListener=new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                parseJsonWithGson(response.toString());
            }
        };
        sendVolleyRequest();

    }

    private void parseJsonWithGson(String string) {
        String strWeek="";
        Gson gson=new Gson();
        Weather weather=gson.fromJson(string,Weather.class);
        mTextviewToday.setText("今天："+String.valueOf(weather.getWCurrent())+"℃");
        for (int i=0;i<weather.getROWS_DETAIL().size();i++){
            strWeek+=weather.getROWS_DETAIL().get(i).getWData()+"：";
            strWeek+=weather.getROWS_DETAIL().get(i).getTemperature()+"℃";
            strWeek+="\n";
        }
        mTextviewWeek.setText(strWeek);
    }

    public static RequestQueue getRequestQueue(Context context) {
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    private void initView() {
        mTextview = (TextView) findViewById(R.id.textview);
        mTextviewToday = (TextView) findViewById(R.id.textview_today);
        mTextview1 = (TextView) findViewById(R.id.textview1);
        mTextviewWeek = (TextView) findViewById(R.id.textview_week);
        mBtnFresh = (Button) findViewById(R.id.btn_fresh);

        mBtnFresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fresh:
                sendVolleyRequest();

                break;
        }
    }

    private void sendVolleyRequest() {
        String strUrl="http://139.199.220.137:8080/api/v2/get_weather";
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("UserName","user1");
        com.android.volley.Request request=new JsonObjectRequest(Request.Method.POST,
                strUrl,new Gson().toJson(hashMap),mVolleyResponseListener,null);
        getRequestQueue(this).add(request);
    }
}
