package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.apk.movie1.Trendingmovieadapter.Trendingmovieadapter;
import com.google.android.material.tabs.TabLayout;

public class TrendingmovieActivity extends AppCompatActivity {
    private static final String TAG = "TrendingmovieActivity";
    TabLayout tabLayout;
    ViewPager pager;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trendingmovie);

        persion_id= getIntent().getStringExtra("persion_id");


        tabLayout=findViewById(R.id.tablayout);
        pager=findViewById(R.id.pager);

        Trendingmovieadapter trendingmovieadapter=new Trendingmovieadapter(getSupportFragmentManager(),persion_id);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(trendingmovieadapter);
    }
}