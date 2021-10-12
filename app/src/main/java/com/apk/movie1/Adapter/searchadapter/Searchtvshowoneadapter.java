package com.apk.movie1.Adapter.searchadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment.Searchtvshowcastfragment;
import com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment.SearchtvshowinfoFragment;
import com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment.Searchtvshowreviewfragment;
import com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment.Searchtvshowsimilerfragment;

public class Searchtvshowoneadapter extends FragmentPagerAdapter {

    String persion_id;

    public Searchtvshowoneadapter(@NonNull FragmentManager fm,String persion_id) {
        super(fm);
        this.persion_id=persion_id;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new SearchtvshowinfoFragment(persion_id);

            case 1:
                return new Searchtvshowcastfragment();

           /* case 2:
                return new Searchtvshowreviewfragment();

            case 3:
                return new Searchtvshowsimilerfragment();*/

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

           /* case 1:
                return "Cast";*/

            case 1:
                return "Reviews";

           /* case 3:
                return "Similer";*/

            default:
                return null;
        }
    }
}
