package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.apk.movie1.Adapter.searchadapter.SearchpersononeAdapter;
import com.google.android.material.tabs.TabLayout;

public class SearchpersononeActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpersonone);

        tab=findViewById(R.id.tab);
        pager=findViewById(R.id.pager);

        SearchpersononeAdapter searchpersononeAdapter=new SearchpersononeAdapter(getSupportFragmentManager());
        tab.setupWithViewPager(pager);
        pager.setAdapter(searchpersononeAdapter);


    }
}