package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.apk.movie1.Adapter.Trendingtvshowadapter.Trendingtvshowadapter;
import com.google.android.material.tabs.TabLayout;

public class TrendingtvshowActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trendingtvshow);

        tabLayout=findViewById(R.id.tablayout);
        pager=findViewById(R.id.pager);

        persion_id=getIntent().getStringExtra("persion_id");

        Trendingtvshowadapter trendingtvshowadapter=new Trendingtvshowadapter(getSupportFragmentManager(),persion_id);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(trendingtvshowadapter);
    }
}