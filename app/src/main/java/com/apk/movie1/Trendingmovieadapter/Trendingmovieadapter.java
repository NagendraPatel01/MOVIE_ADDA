package com.apk.movie1.Trendingmovieadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendingmoviefragment.TrendingmoviecastFragment;
import com.apk.movie1.Trendingmoviefragment.TrendingmovieinfoFragment;
import com.apk.movie1.Trendingmoviefragment.TrendingmoviereviewFragment;
import com.apk.movie1.Trendingmoviefragment.TrendingmoviesimilerFragment;

public class Trendingmovieadapter extends FragmentPagerAdapter {

       String persion_id;

    public Trendingmovieadapter(@NonNull FragmentManager fm ,String persion_id1) {
        super(fm);

        this.persion_id=persion_id1;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
            return  new TrendingmovieinfoFragment(persion_id);

            case 1:
                return  new TrendingmoviereviewFragment(persion_id);


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
                return "Info";


            case 1:
                return "Reviews";



            default:
                return null;
        }
    }
}
