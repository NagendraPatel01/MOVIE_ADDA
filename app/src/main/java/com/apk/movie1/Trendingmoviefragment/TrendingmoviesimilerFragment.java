package com.apk.movie1.Trendingmoviefragment;

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
import com.apk.movie1.Trendingmovieadapter.TrendingmoviesimilerAdapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingmoviesimilermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrendingmoviesimilerFragment extends Fragment {

    private static final String TAG = "TrendingmoviesimilerFra";
    RecyclerView recycle;
    String url;
    RequestQueue queue;
    StringRequest request;
    List<Trendingmoviesimilermodel> list;
    String persion_id;

  public  TrendingmoviesimilerFragment(String persion_id){

      this.persion_id=persion_id;
  }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trendingmoviesimiler, container, false);

        url="https://api.themoviedb.org/3/movie/"+persion_id+"/similar";

        recycle = view.findViewById(R.id.recycle);
        list = new ArrayList<>();

        queue = Volley.newRequestQueue(getContext());
        request = new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        Trendingmoviesimilermodel trendingmoviesimilermodel = new Trendingmoviesimilermodel();
                        Log.d(TAG, "title: "+jsonObject1.getString("title"));

                        if (jsonObject1.has("title") && !jsonObject1.isNull("title")) {

                            trendingmoviesimilermodel.setTitle(jsonObject1.getString("title"));
                        }



                        if (jsonObject1.has("poster_path") && !jsonObject1.isNull("poster_path")) {

                            trendingmoviesimilermodel.setPoster_path(jsonObject1.getString("poster_path"));
                        }

                        trendingmoviesimilermodel.setRelease_date(jsonObject1.getString("release_date"));
                        trendingmoviesimilermodel.setVote_average(jsonObject1.getString("vote_average"));
                        list.add(trendingmoviesimilermodel);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager);

                        TrendingmoviesimilerAdapter trendingmoviesimilerAdapter = new TrendingmoviesimilerAdapter(getContext(), list);
                        recycle.setAdapter(trendingmoviesimilerAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error);
            }
        });
        queue.add(request);

        return view;
    }
}