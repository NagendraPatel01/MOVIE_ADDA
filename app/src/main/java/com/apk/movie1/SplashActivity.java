package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.leo.simplearcloader.SimpleArcLoader;

public class SplashActivity extends AppCompatActivity {

    SimpleArcLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        loader=findViewById(R.id.loader);



            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);

                    finish();
                }
            },3000);


    }
}