package com.sziit.sd;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFile;
    private Button mBtnVideo;
    private Button mBtnAudio;
    private ListView mListview;

    private ArrayAdapter<String> mArrayAdapter;
    private List<String> data=new ArrayList<>();
    private Bundle bundle=new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        getSdPermissions();
    }

    private void getSdPermissions() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                            ,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }
                else {
                    Toast.makeText(this,"You denied the permissions",Toast.LENGTH_LONG).show();
                }
        }
    }

    private void initData() {
        mArrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        mListview.setAdapter(mArrayAdapter);
    }


    private void initView() {
        mBtnFile = (Button) findViewById(R.id.btn_file);
        mBtnVideo = (Button) findViewById(R.id.btn_video);
        mBtnAudio = (Button) findViewById(R.id.btn_audio);
        mListview = (ListView) findViewById(R.id.listview);

        mBtnFile.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnAudio.setOnClickListener(this);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path=data.get(position);
                String prefix=path.substring(path.lastIndexOf(".")+1);
                if (prefix.equals("mp3")||prefix.equals("flac")){
                    bundle.putString("mp3",path);
                    Intent intent=new Intent(MainActivity.this,AudioPlayer.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (prefix.equals("mp4")||prefix.equals("wmv")){
                    bundle.putString("video",path);
                    Intent intent=new Intent(MainActivity.this,VideoPlayer.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (prefix.equals("png")||prefix.equals("jpg")||prefix.equals("gif")){
                    bundle.putString("pic",path);
                    Intent intent=new Intent(MainActivity.this,PictureActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file:
                listPic();
                break;
            case R.id.btn_video:
                listVideo();
                break;
            case R.id.btn_audio:
                listAudio();
                break;
        }
    }

    private void listAudio() {
        mArrayAdapter.clear();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor=contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,MediaStore.Audio.Media.DATA}
                ,null,null,MediaStore.Audio.Media.DATA);
        if(cursor!=null){
            while (cursor.moveToNext()){
                data.add(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Audio.Media.DATA
                )));
            }
        }
    }

    private void listVideo() {
        mArrayAdapter.clear();
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA},
                null,null,MediaStore.Video.Media.DATA
        );
        if (cursor!=null){
            while (cursor.moveToNext()){
                data.add(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Video.Media.DATA
                )));
            }
        }
    }

    private void listPic() {
        mArrayAdapter.clear();
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA},
                null,null,MediaStore.Images.Media.DATA
        );
        if (cursor!=null){
            while (cursor.moveToNext()){
                data.add(cursor.getString(cursor.getColumnIndex(
                        MediaStore.Images.Media.DATA
                )));
            }
        }
    }
}
