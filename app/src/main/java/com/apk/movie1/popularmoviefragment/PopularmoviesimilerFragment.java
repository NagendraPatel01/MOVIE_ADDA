package com.apk.movie1.popularmoviefragment;

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
import com.apk.movie1.Adapter.Popularmoviesimileradapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Popularmoviesimilermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PopularmoviesimilerFragment extends Fragment {

    private static final String TAG = "PopularmoviesimilerFrag";
    RequestQueue queue;
    StringRequest request;
    List<Popularmoviesimilermodel>list;
    String url;
    RecyclerView recycle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_popularmoviesimiler, container, false);

        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();
        url="https://api.themoviedb.org/3/movie/399566/similar";

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Popularmoviesimilermodel popularmoviesimilermodel=new Popularmoviesimilermodel();
                        popularmoviesimilermodel.setPoster_path(jsonObject1.getString("poster_path"));
                        popularmoviesimilermodel.setRelease_date(jsonObject1.getString("release_date"));
                        popularmoviesimilermodel.setTitle(jsonObject1.getString("title"));
                        popularmoviesimilermodel.setVote_average(jsonObject1.getString("vote_average"));
                        list.add(popularmoviesimilermodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Popularmoviesimileradapter popularmoviesimileradapter=new Popularmoviesimileradapter(getContext(),list);
                        recycle.setAdapter(popularmoviesimileradapter);
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