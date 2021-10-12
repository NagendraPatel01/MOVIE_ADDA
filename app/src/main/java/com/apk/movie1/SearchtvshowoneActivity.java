package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.apk.movie1.Adapter.searchadapter.SearchmovieoneAdapter;
import com.apk.movie1.Adapter.searchadapter.Searchtvshowoneadapter;
import com.google.android.material.tabs.TabLayout;

public class SearchtvshowoneActivity extends AppCompatActivity {


    TabLayout tab;
    ViewPager pager;
    String persion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchtvshowone);

        persion_id= getIntent().getStringExtra("persion_id");
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);

        Searchtvshowoneadapter searchtvshowoneadapter = new Searchtvshowoneadapter(getSupportFragmentManager(),persion_id);
        tab.setupWithViewPager(pager);
        pager.setAdapter(searchtvshowoneadapter);
    }
}
