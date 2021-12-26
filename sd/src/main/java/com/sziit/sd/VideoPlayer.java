package com.sziit.sd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class VideoPlayer extends AppCompatActivity {

    private VideoView mVideoView;
    private String path;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        initView();
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        mVideoView.setVideoPath(path);
        mVideoView.setMediaController(mediaController);
    }


    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        Bundle bundle=getIntent().getExtras();
        path=bundle.getString("video");
        mediaController=new MediaController(this);
    }
}
