package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.apk.movie1.populartvshowadapter.Populartvshowadapter;
import com.google.android.material.tabs.TabLayout;

public class PopulartvshowActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populartvshow);

        tabLayout=findViewById(R.id.tablayout);
        pager=findViewById(R.id.pager);

        persion_id=getIntent().getStringExtra("persion_id");

        Populartvshowadapter populartvshowadapter=new Populartvshowadapter(getSupportFragmentManager(),persion_id);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(populartvshowadapter);
    }
}