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
import com.apk.movie1.trendingpersonadapter.TrendingpersonmovieAdapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingpersonmoviemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendingpersonmovieFragment extends Fragment {
    private static final String TAG = "TrendingpersonmovieFrag";

    RecyclerView recycle;
    RequestQueue queue;
    StringRequest request;
    List<Trendingpersonmoviemodel>list;
    String person_id;
    String url;

   public TrendingpersonmovieFragment(String person_id){
       this.person_id = person_id;
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_trendingpersonmovie, container, false);

        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();

        Log.d(TAG, "onCreateView123: "+person_id);
        url="https://api.themoviedb.org/3/person/"+person_id+"/movie_credits";

       // "https://api.themoviedb.org/3/person/"+person_id+"/movie_credits?api_key=c4824776bf6f08433a4c4e7cd75a6acc&language=en-US"

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse12: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("cast");

                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);


                            Trendingpersonmoviemodel trendingpersonmoviemodel=new Trendingpersonmoviemodel();

                            trendingpersonmoviemodel.setTitle(jsonObject1.getString("title"));

                            Log.d(TAG, "onResponse12: "+jsonObject1.getString("title"));

                        if (jsonObject1.has("backdrop_path") && !jsonObject1.isNull("backdrop_path")) {

                            trendingpersonmoviemodel.setBackdrop_path(jsonObject1.getString("backdrop_path"));
                        }

                            trendingpersonmoviemodel.setRelease_date(jsonObject1.getString("release_date"));
                            trendingpersonmoviemodel.setVote_average(jsonObject1.getString("vote_average"));
                            list.add(trendingpersonmoviemodel);

                             LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                             recycle.setLayoutManager(linearLayoutManager);

                              TrendingpersonmovieAdapter trendingpersonmovieAdapter=new TrendingpersonmovieAdapter(getContext(),list);
                             recycle.setAdapter(trendingpersonmovieAdapter);



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
queue.add(request);
        return view;
    }
}