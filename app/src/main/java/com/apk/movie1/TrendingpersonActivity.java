package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.apk.movie1.trendingpersonadapter.Trendingpersonadapter;
import com.google.android.material.tabs.TabLayout;

public class TrendingpersonActivity extends AppCompatActivity {

    private static final String TAG = "TrendingpersonActivity";
    TabLayout tab;
    ViewPager pager;
    String person_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trendingperson);

        person_id = getIntent().getStringExtra("person_id");
        Log.d(TAG, "onCreate: "+person_id);

        tab=findViewById(R.id.tab);
        pager=findViewById(R.id.pager);

        Trendingpersonadapter trendingpersonadapter=new Trendingpersonadapter(getSupportFragmentManager(),person_id);

        tab.setupWithViewPager(pager);
        pager.setAdapter(trendingpersonadapter);
    }
}