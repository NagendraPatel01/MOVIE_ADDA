package com.apk.movie1.populartvshowadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.PopularTVShowFragment.PopularTVinfoFragment;
import com.apk.movie1.PopularTVShowFragment.PopularTvreviewFragment;
import com.apk.movie1.fragment.Homefragment;
import com.apk.movie1.fragment.MenuFragment;

public class Populartvshowadapter extends FragmentPagerAdapter {

    String persion_id;


    public Populartvshowadapter(FragmentManager fm, String persion_id) {
        super(fm);
        this.persion_id = persion_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new PopularTVinfoFragment(persion_id);
            case 1:
                return new PopularTvreviewFragment(persion_id);

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
