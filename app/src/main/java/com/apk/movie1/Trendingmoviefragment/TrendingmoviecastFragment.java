package com.apk.movie1.Trendingmoviefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingmoviecastmodel;

import java.util.ArrayList;
import java.util.List;


public class TrendingmoviecastFragment extends Fragment {

        String url="";
        RequestQueue queue;
        StringRequest request;
        List<Trendingmoviecastmodel>list;
        RecyclerView recycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_trendingmoviecast, container, false);

        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();

        return view;
    }
}