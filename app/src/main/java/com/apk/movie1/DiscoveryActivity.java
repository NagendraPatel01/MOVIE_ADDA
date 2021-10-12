package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.apk.movie1.Adapter.searchadapter.Discoveryadapter;
import com.apk.movie1.Trendingmovieadapter.Trendingmovieadapter;
import com.google.android.material.tabs.TabLayout;

public class DiscoveryActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager pager;
    EditText edit;
    ImageView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        tab=findViewById(R.id.tab);
        pager=findViewById(R.id.pager);
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Discoveryadapter discoveryadapter=new Discoveryadapter(getSupportFragmentManager(),edit.getText().toString());
                tab.setupWithViewPager(pager);
                pager.setAdapter(discoveryadapter);

            }
        });


    }
}