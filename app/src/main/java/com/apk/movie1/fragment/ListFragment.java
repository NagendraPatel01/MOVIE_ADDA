package com.apk.movie1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apk.movie1.Adapter.ListAdapter;
import com.apk.movie1.R;
import com.google.android.material.tabs.TabLayout;


public class ListFragment extends Fragment {

TabLayout tab;
ViewPager pager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_list, container, false);

        tab=view.findViewById(R.id.tab);
        pager=view.findViewById(R.id.pager);

        ListAdapter listAdapter=new ListAdapter(getChildFragmentManager());
        tab.setupWithViewPager(pager);
        pager.setAdapter(listAdapter);

        return view;
    }
}