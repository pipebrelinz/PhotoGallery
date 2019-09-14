package com.example.photogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonCamera;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_VIDEO_CAPTURE = 2;
    private ImageView image;
    private ImageButton fr;
    private ImageButton fl;
    private ImageButton buttonVideo;
    private ArrayList<Bitmap>photos;
    private Bitmap imageB;
    private Uri videoURL;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = (ImageButton) findViewById(R.id.buttonCamera);
        image = (ImageView) findViewById(R.id.imagePhoto);
        fr = (ImageButton) findViewById(R.id.fr);
        fl = (ImageButton) findViewById(R.id.fl);
        buttonVideo = (ImageButton) findViewById(R.id.buttonVideo);
        photos = new ArrayList<>();

    }

    public void tomarFoto (View v){
        takeAPicture();
    }
    public void grabarVideo (View v) {recordAVideo();}

    public void photoR (View v){
        if(!photos.isEmpty()){
            int i = photos.indexOf(imageB);
            if(photos.size()>i){
                Bitmap photoR = photos.get((i+1)%photos.size());
                image.setImageBitmap(photoR);
                imageB=photoR;
            }
        }
    }

    public void photoL (View v){
        if(!photos.isEmpty()){
            int i = photos.indexOf(imageB);
            if (i < 1) i = photos.size();
            if(photos.size()>=i){
                Bitmap photoL = photos.get((i-1)%photos.size());
                image.setImageBitmap(photoL);
                imageB=photoL;
            }
        }
    }


    public void takeAPicture(){
        Toast.makeText(this, "@Tomar Foto", Toast.LENGTH_SHORT).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void recordAVideo(){
        Intent recordVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (recordVideoIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(recordVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "@Here is your picture", Toast.LENGTH_SHORT).show();
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            imageB = (Bitmap) extra.get("data");
            image.setImageBitmap(imageB);
            photos.add(imageB);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Intent videoActivity = new Intent(this, VideoActivity.class);
            videoActivity.putExtra("videoURL", data.getData().toString());
            startActivity(videoActivity);
        }
    }
}