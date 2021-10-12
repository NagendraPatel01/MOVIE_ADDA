package com.apk.movie1.Adapter.Trendingtvshowadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendingtvshowfragment.TrendingtvshowinfoFragment;
import com.apk.movie1.Trendingtvshowfragment.TrendingtvshowrivewFragment;
import com.apk.movie1.fragment.MenuFragment;
import com.apk.movie1.Trendingtvshowfragment.TrendigtvshowsimilerFragment;

public class Trendingtvshowadapter extends FragmentPagerAdapter {

    String persion_id;

    public Trendingtvshowadapter(@NonNull FragmentManager fm,String persion_id) {
        super(fm);

        this.persion_id=persion_id;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return  new TrendingtvshowinfoFragment(persion_id);


            case 1:
                return  new TrendingtvshowrivewFragment(persion_id);



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
