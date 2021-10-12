package com.apk.movie1.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.apk.movie1.MenuAppinfoActivity;
import com.apk.movie1.R;


public class MenuFragment extends Fragment {

RelativeLayout appinfo,shareapk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_menu, container, false);

        appinfo=view.findViewById(R.id.appinfo);
        shareapk=view.findViewById(R.id.shareapk);

        shareapk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });

        appinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), MenuAppinfoActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}