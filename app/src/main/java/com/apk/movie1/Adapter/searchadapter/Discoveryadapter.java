package com.apk.movie1.Adapter.searchadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.searchmoviefragment.Searchmoviecastfragment;
import com.apk.movie1.searchmoviefragment.SearchmovieinfoFragment;
import com.apk.movie1.searchmoviefragment.SearchmoviereviewFragment;
import com.apk.movie1.searchmoviefragment.Searchmoviesimilarfragment;

public class Discoveryadapter extends FragmentPagerAdapter {

    String query;

    public Discoveryadapter(@NonNull FragmentManager fm,String query) {

        super(fm);

        this.query=query;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new Searchmoviecastfragment(query);

            case 1:
                return new Searchmoviecastfragment(query);

           case 2:
                return new Searchmoviesimilarfragment();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Movie";

            case 1:
                return "Tvshow";

            case 2:
                return "People";

            default:
                return null;
        }
    }
}
