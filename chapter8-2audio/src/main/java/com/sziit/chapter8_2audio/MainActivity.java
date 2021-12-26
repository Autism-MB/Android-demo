package com.sziit.chapter8_2audio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , SeekBar.OnSeekBarChangeListener {

    private TextView mTvSongName;
    private TextView mTvSongLrc;
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnReset;
    private SeekBar mSeekBar;
    private MediaPlayer mediaPlayer;
    private myThread thread;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mediaPlayer = new MediaPlayer();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initMediaPlayer();
        }
    }


    private void initMediaPlayer()  {
        File file = new File(Environment.getExternalStorageDirectory(),"Lauren Chen.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            mTvSongName.setText("准备播放："+"mavic.mp3");
            thread=new myThread();
            thread.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initView() {
        mTvSongName = (TextView) findViewById(R.id.tv_song_name);
        mTvSongLrc = (TextView) findViewById(R.id.tv_song_lrc);
        mBtnPlay = (Button) findViewById(R.id.btn_play);
        mBtnPause = (Button) findViewById(R.id.btn_pause);
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mBtnPlay.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    mTvSongName.setText("正在播放的："+"mavic.mp3");
                    thread = new myThread();
                    thread.start();
                }
                break;
            case R.id.btn_pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    mTvSongName.setText("正在播放的："+"mavic.mp3"+";已暂停");
                }
                break;
            case R.id.btn_reset:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            thread.stop();
        }
    }
    //  值改变
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }
    //值改变前
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //值改变后
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mediaPlayer.isPlaying()){
            //mediaPlayer.seekTo(seekBar.getProgress());
            int duration = mediaPlayer.getDuration();//总时长毫米单位
            int seek=duration*(seekBar.getProgress())/100;
            //int time = seek/100;
            mediaPlayer.seekTo(seek);
        }
    }
    //设置一个线程运行进度条
    class myThread extends Thread{
        @Override
        public  void run(){
            super.run();
            //判断当前播放位置是否小于总时长
            while (mSeekBar.getProgress()<=mSeekBar.getMax()&&mediaPlayer.isPlaying()){
                progress = mediaPlayer.getCurrentPosition()*100/mediaPlayer.getDuration();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置进度条当前位置为音频播放位置
                        mSeekBar.setProgress(progress);
                    }
                });
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
