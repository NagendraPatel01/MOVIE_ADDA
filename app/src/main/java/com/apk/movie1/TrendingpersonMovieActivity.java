package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.apk.movie1.trendingpersonadapter.TrendimgPersonmovieOneAdapter;
import com.google.android.material.tabs.TabLayout;

public class TrendingpersonMovieActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tab;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trendingperson_movie);

        persion_id=getIntent().getStringExtra("persion_id");
        Log.d("dfghjk", "fghjkfghj"+persion_id);

        tab=findViewById(R.id.tab);
        pager=findViewById(R.id.pager);

        TrendimgPersonmovieOneAdapter trendimgPersonmovieOneAdapter=new TrendimgPersonmovieOneAdapter(getSupportFragmentManager(),persion_id);

        tab.setupWithViewPager(pager);
        pager.setAdapter(trendimgPersonmovieOneAdapter);
    }
}