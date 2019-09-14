package com.example.photogallery;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView video;
    private String intentData;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.videoactivity);
        video = (VideoView) findViewById(R.id.video);
        String aux = getIntent().getStringExtra("videoURL");
        intentData = aux;
        video.setVideoPath(aux);
        video.start();
    }

    public void play (View v){
        if(intentData != null){
            video.stopPlayback();
            video.setVideoPath(intentData);
            video.start();
        }
    }
}
