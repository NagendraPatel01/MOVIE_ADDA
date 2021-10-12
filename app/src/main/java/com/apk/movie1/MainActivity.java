package com.apk.movie1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apk.movie1.fragment.Homefragment;
import com.apk.movie1.fragment.ListFragment;
import com.apk.movie1.fragment.MenuFragment;
import com.apk.movie1.fragment.SearchFragment;
import com.github.mzule.fantasyslide.FantasyDrawerLayout;
import com.github.mzule.fantasyslide.FantasyListener;
import com.github.mzule.fantasyslide.SideBar;
import com.github.mzule.fantasyslide.SimpleFantasyListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom);
        frame=findViewById(R.id.frame);


        replace(new Homefragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home0:
                        replace(new Homefragment());
                        break;

                    case R.id.home1:
                        replace(new SearchFragment());
                        break;

                   /* case R.id.home2:
                        replace(new ListFragment());
                        break;*/


                    case R.id.home3:
                        replace(new MenuFragment());
                        break;


                }

                return false;
            }
        });


    }

    public void replace(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

}