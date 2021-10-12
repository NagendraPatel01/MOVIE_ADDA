package com.apk.movie1.trendingpersonadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendinpersonfrgment.TrendingpersoninfoFragment;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersonmovieFragment;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersontvshowFragment;

public class Trendingpersonadapter extends FragmentPagerAdapter {

    String person_id;

    public Trendingpersonadapter(@NonNull FragmentManager fm,String person_idl) {
        super(fm);
        this.person_id = person_idl;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new TrendingpersoninfoFragment(person_id);

            case 1:
                return new TrendingpersonmovieFragment(person_id);

            case 2:
                return new TrendingpersontvshowFragment(person_id);

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
                return "info";

            case 1:
                return "Movies";

            case 2:
                return "Tv Shows";

            default:
                return null;
        }
    }
}
