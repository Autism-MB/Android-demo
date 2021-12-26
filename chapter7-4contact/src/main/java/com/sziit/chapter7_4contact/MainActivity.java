package com.sziit.chapter7_4contact;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final int REQUST_READ_CONTACT = 1;
    private static final Uri CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private ArrayList<HashMap<String, Object>> arrayList;
    private SearchView mSearchView;
    private ListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getReadContactPermission();
    }

    private void getReadContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        //检查应用是否有读取通讯录权限
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            //没有权限的话需要申请权限
        } else {
            readContacts();
            //如果有权限的话直接调用读取通讯录函数
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUST_READ_CONTACT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "YOU DENY THE READ_CONTACTS PERMISSON", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private void readContacts() {
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = this.getContentResolver();
        //执行查询，获取通讯录信息
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {//逐条查询通讯录数据库数据
            while (cursor.moveToNext()) {
                String strName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String strPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", strName);
                hashMap.put("number", strPhoneNumber);
                arrayList.add(hashMap);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.activity_main_contactitem, new String[]{"name"}, new int[]{R.id.tv_name});
        mListview.setAdapter(simpleAdapter);
    }

    private void initView() {

        mListview = (ListView) findViewById(R.id.listview);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String strName = (String) arrayList.get(position).get("name");
        String strNumber = (String) arrayList.get(position).get("number");
        Intent intent = new Intent(this, com.sziit.chapter7_4contact.ContactsContract.class);
        intent.putExtra("name", strName);
        intent.putExtra("number", strNumber);
        startActivity(intent);
    }
}
