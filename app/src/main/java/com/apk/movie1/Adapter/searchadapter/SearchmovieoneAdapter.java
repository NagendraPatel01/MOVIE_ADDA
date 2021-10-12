package com.apk.movie1.Adapter.searchadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apk.movie1.fragment.Homefragment;
import com.apk.movie1.fragment.MenuFragment;
import com.apk.movie1.searchmoviefragment.Searchmoviecastfragment;
import com.apk.movie1.searchmoviefragment.SearchmovieinfoFragment;
import com.apk.movie1.searchmoviefragment.SearchmoviereviewFragment;
import com.apk.movie1.searchmoviefragment.Searchmoviesimilarfragment;

public class SearchmovieoneAdapter extends FragmentPagerAdapter {

    String persion_id;

    public SearchmovieoneAdapter(@NonNull FragmentManager fm,String persion_id) {
        super(fm);
        this.persion_id=persion_id;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new SearchmovieinfoFragment(persion_id);

           /* case 1:
                return new Searchmoviecastfragment();
*/
            case 1:
                return new SearchmoviereviewFragment(persion_id);

          /*  case 3:
                return new Searchmoviesimilarfragment();*/
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

         /*   case 3:
                return "Similer";*/

            default:
                return null;
        }
    }
}
