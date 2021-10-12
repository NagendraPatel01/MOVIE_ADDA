package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    String ankit;
    ImageView img;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ankit=getIntent().getStringExtra("ankit");

        img=findViewById(R.id.img);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+ankit).into(img);
    }
}