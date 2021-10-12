package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.apk.movie1.Adapter.Popularmovieadapter;
import com.google.android.material.tabs.TabLayout;

public class PopularmovieActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popularmovie);

        persion_id=getIntent().getStringExtra("persion_id");
        tabLayout=findViewById(R.id.tablayout);
        pager=findViewById(R.id.pager);

        Popularmovieadapter popularmovieadapter=new Popularmovieadapter(getSupportFragmentManager(),persion_id);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(popularmovieadapter);

    }
}