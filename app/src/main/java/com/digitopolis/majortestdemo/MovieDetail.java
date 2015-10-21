package com.digitopolis.majortestdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by User on 10/16/2015.
 */
public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        VideoCustom();
    }

    public void VideoCustom() {

        Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.movie); // play from local file

        MediaController mediacontroller = new MediaController(this);

//        mediacontroller.set

        VideoView video = (VideoView) this.findViewById(R.id.videoView);

        video.setMediaController(mediacontroller);

        video.setVideoURI(uri);

        video.requestFocus();

        video.start();
    }
}
