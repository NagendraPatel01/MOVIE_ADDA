package com.apk.movie1.trendingpersonadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendinpersonfrgment.TrendingPersonMovieoneFragment;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersoninfoFragment;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersonmovieFragment;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersontvshowFragment;

public class TrendimgPersonmovieOneAdapter extends FragmentPagerAdapter {

    String persion_id;

    public TrendimgPersonmovieOneAdapter(@NonNull FragmentManager fm, String persion_id) {
        super(fm);
        this.persion_id = persion_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TrendingPersonMovieoneFragment(persion_id);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        switch (position){

            case 0:
                return "info";


            default:
                return null;
        }
    }
}
