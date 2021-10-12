package com.apk.movie1.Adapter.searchadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.fragment.Homefragment;
import com.apk.movie1.fragment.MenuFragment;
import com.apk.movie1.fragment.SearchFragment;

public class SearchpersononeAdapter extends FragmentPagerAdapter {

    public SearchpersononeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new SearchFragment();

            case 1:
                return new MenuFragment();

            case 2:
                return new Homefragment();


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
                return "Info";

            case 1:
                return "movie";

            case 2:
                return "tv show";


            default:
                return null;
        }
    }
}
