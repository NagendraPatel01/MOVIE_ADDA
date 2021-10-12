package com.apk.movie1.Trendinpersonfrgment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.movie1.model.Trendingpersoninfomodel;
import com.apk.movie1.trendingpersonadapter.TrendingpersoninfoAdapter;
import com.apk.movie1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrendingpersoninfoFragment extends Fragment {
    private static final String TAG = "TrendingpersoninfoFragm";
RecyclerView recycle;
 RequestQueue queue;
 StringRequest request;
 String url;
 List<Trendingpersoninfomodel>list;
 String persion_id;

 public  TrendingpersoninfoFragment(String persion_id){
     this.persion_id=persion_id;
 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_trendingpersoninfo, container, false);



        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();


       // https://api.themoviedb.org/3/person/287?api_key=c4824776bf6f08433a4c4e7cd75a6acc
        url="https://api.themoviedb.org/3/person/"+persion_id;

        queue= Volley.newRequestQueue(getContext());
        request= new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Trendingpersoninfomodel trendingpersoninfomodel=new Trendingpersoninfomodel();

                    trendingpersoninfomodel.setBiography(jsonObject.getString("biography"));
                    trendingpersoninfomodel.setBirthday(jsonObject.getString("birthday"));
                    trendingpersoninfomodel.setProfile_path(jsonObject.getString("profile_path"));
                    trendingpersoninfomodel.setName(jsonObject.getString("name"));
                    list.add(trendingpersoninfomodel);


                     LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                     recycle.setLayoutManager(linearLayoutManager);


                    TrendingpersoninfoAdapter trendingpersoninfoAdapter=new TrendingpersoninfoAdapter(getContext(),list);
                     recycle.setAdapter(trendingpersoninfoAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);

            }
        });
        queue.add(request);
        return view;
    }
}