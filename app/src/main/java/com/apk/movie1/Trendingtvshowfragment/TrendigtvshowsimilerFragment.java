package com.apk.movie1.Trendingtvshowfragment;

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
import com.apk.movie1.Adapter.Trendingtvshowadapter.TrendingtvshowsimilerAdapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingtvshowsimilermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendigtvshowsimilerFragment extends Fragment {
    private static final String TAG = "TrendigtvshowsimilerFra";

    String url="https://api.themoviedb.org/3/tv/88396/similar";
    RequestQueue queue;
    StringRequest request;
    List<Trendingtvshowsimilermodel>list;
    RecyclerView recycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trendigtvshowsimiler, container, false);

        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();

        queue= Volley.newRequestQueue(getContext());

        request=new StringRequest(Request.Method.GET, url+"?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        Trendingtvshowsimilermodel trendingtvshowsimilermodel = new Trendingtvshowsimilermodel();

                        Log.d(TAG, "onResponse: "+jsonObject1.getString("name"));

                        trendingtvshowsimilermodel.setName(jsonObject1.getString("name"));
                        trendingtvshowsimilermodel.setPoster_path(jsonObject1.getString("poster_path"));
                        trendingtvshowsimilermodel.setFirst_air_date(jsonObject1.getString("first_air_date"));
                        trendingtvshowsimilermodel.setVote_average(jsonObject1.getString("vote_average"));
                        list.add(trendingtvshowsimilermodel);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager);

                        TrendingtvshowsimilerAdapter trendingtvshowsimilerAdapter = new TrendingtvshowsimilerAdapter(getContext(), list);
                        recycle.setAdapter(trendingtvshowsimilerAdapter);
                    }
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