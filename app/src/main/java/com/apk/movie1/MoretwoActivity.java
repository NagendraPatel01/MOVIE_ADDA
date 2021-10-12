package com.apk.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.movie1.homeadapter.Hometrendingmovieadapter;
import com.apk.movie1.model.Searchmoviemodel;
import com.apk.movie1.model.Trendingmoviemodel;
import com.apk.movie1.model.Trendingpersonmodel;
import com.apk.movie1.moreadapter.Moretwoadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoretwoActivity extends AppCompatActivity {

    private static final String TAG = "MoretwoActivity";
    RecyclerView recycle;

    String url1="https://api.themoviedb.org/3/trending/movie/week";
    RequestQueue queue;
    StringRequest request;
    List<Searchmoviemodel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moretwo);

        recycle=findViewById(R.id.recycle);
        list=new ArrayList<>();


        queue= Volley.newRequestQueue(MoretwoActivity.this);
        request=new StringRequest(Request.Method.GET, url1 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){


                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Searchmoviemodel searchmoviemodel=new Searchmoviemodel();

                        searchmoviemodel.setPoster_path(jsonObject1.getString("poster_path"));
                        searchmoviemodel.setRelease_date(jsonObject1.getString("release_date"));
                        searchmoviemodel.setTitle(jsonObject1.getString("title"));
                        searchmoviemodel.setVote_average(jsonObject1.getString("vote_average"));
                        searchmoviemodel.setId(jsonObject1.getString("id"));
                        list.add(searchmoviemodel);



                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MoretwoActivity.this, RecyclerView.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager1);
                        Moretwoadapter moretwoadapter = new Moretwoadapter(MoretwoActivity.this,list);
                        recycle.setAdapter(moretwoadapter);

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



        /*LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MoretwoActivity.this,RecyclerView.VERTICAL,false);
        recycle.setLayoutManager(linearLayoutManager);*/
    }
}