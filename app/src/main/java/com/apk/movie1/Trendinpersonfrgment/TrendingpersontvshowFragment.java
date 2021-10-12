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
import com.apk.movie1.model.Trendingpersontvshowmodel;
import com.apk.movie1.trendingpersonadapter.TrendingpersontvshowAdapter;
import com.apk.movie1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendingpersontvshowFragment extends Fragment {

    private static final String TAG = "TrendingpersontvshowFra";
  RecyclerView recycle;
  String url;
  String persion_id;
  RequestQueue queue;
  StringRequest request;
  List<Trendingpersontvshowmodel>list;


    public TrendingpersontvshowFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_trendingpersontvshow, container, false);

        recycle=view.findViewById(R.id.recycle);
        list=new ArrayList<>();

        url="https://api.themoviedb.org/3/person/"+persion_id+"/tv_credits";

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("cast");
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Trendingpersontvshowmodel trendingpersontvshowmodel=new Trendingpersontvshowmodel();

                        trendingpersontvshowmodel.setFirst_air_date(jsonObject1.getString("first_air_date"));
                        trendingpersontvshowmodel.setName(jsonObject1.getString("name"));
                        trendingpersontvshowmodel.setPoster_path(jsonObject1.getString("poster_path"));
                        trendingpersontvshowmodel.setVote_average(jsonObject1.getString("vote_average"));
                        list.add(trendingpersontvshowmodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                         recycle.setLayoutManager(linearLayoutManager);

                         TrendingpersontvshowAdapter trendingpersontvshowAdapter=new TrendingpersontvshowAdapter(getContext(),list);
                         recycle.setAdapter(trendingpersontvshowAdapter);

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