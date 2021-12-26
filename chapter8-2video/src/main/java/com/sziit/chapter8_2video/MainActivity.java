package com.sziit.chapter8_2video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , SeekBar.OnSeekBarChangeListener {

    private TextView mTvSongName;
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnReset;
    private SeekBar mSeekBar;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPlayer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void initVideoPlayer() {
        File file = new File("/sdcard/mv.mp4");
        mVideoView.setVideoPath(file.getPath());
    }

    private void initView() {
        mTvSongName = (TextView) findViewById(R.id.tv_song_name);
        mBtnPlay = (Button) findViewById(R.id.btn_play);
        mBtnPause = (Button) findViewById(R.id.btn_pause);
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mVideoView = (VideoView) findViewById(R.id.videoView);

        mBtnPlay.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (!mVideoView.isPlaying()) {
                    mVideoView.start();
                    mTvSongName.setText("正在播放的："+"movie.mp4");
                }
                break;
            case R.id.btn_pause:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    mTvSongName.setText("正在播放的："+"movie.mp4"+";已暂停");
                }
                break;
            case R.id.btn_reset:
                if (mVideoView.isPlaying()) {
                    mVideoView.resume();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mVideoView.isPlaying()) {
            //mediaPlayer.seekTo(seekBar.getProgress());
            int duration = mVideoView.getDuration();//总时长毫米单位
            int seek = duration * (seekBar.getProgress()) / 100;
            //int time = seek/100;
            mVideoView.seekTo(seek);
        }
    }
}
