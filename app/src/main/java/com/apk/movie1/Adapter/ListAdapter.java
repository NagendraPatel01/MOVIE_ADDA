package com.apk.movie1.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment.Searchtvshowsimilerfragment;
import com.apk.movie1.fragment.Homefragment;
import com.apk.movie1.fragment.MenuFragment;
import com.apk.movie1.fragment.SearchFragment;
import com.apk.movie1.searchmoviefragment.Searchmoviecastfragment;
import com.apk.movie1.searchmoviefragment.Searchmoviesimilarfragment;

public class ListAdapter extends FragmentPagerAdapter {

    public ListAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){


            case 0:
                return new Searchmoviecastfragment("");

            case 1:
                return new Searchtvshowsimilerfragment();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "movie";

            case 1:
                return "Tv show";

            default:
                return null;
        }
    }
}
