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
import com.apk.movie1.homeadapter.Hometrendingtvshowadapter;
import com.apk.movie1.model.Searchmoviemodel;
import com.apk.movie1.model.Searchtvshowmodel;
import com.apk.movie1.model.Trendingtvshowmodel;
import com.apk.movie1.moreadapter.Morethreeadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MorethreeActivity extends AppCompatActivity {

    private static final String TAG = "MoretwoActivity";
    RecyclerView recycle;
    String url2="https://api.themoviedb.org/3/trending/tv/week";
    RequestQueue queue;
    StringRequest request;
    List<Searchtvshowmodel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morethree);

        recycle=findViewById(R.id.recycle);
        list=new ArrayList<>();

        queue= Volley.newRequestQueue(MorethreeActivity.this);
        request=new StringRequest(Request.Method.GET, url2 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Searchtvshowmodel searchtvshowmodel=new Searchtvshowmodel();
                        searchtvshowmodel.setPoster_path(jsonObject1.getString("poster_path"));
                        searchtvshowmodel.setFirst_air_date(jsonObject1.getString("first_air_date"));
                        searchtvshowmodel.setName(jsonObject1.getString("name"));
                        searchtvshowmodel.setVote_average(jsonObject1.getString("vote_average"));
                        searchtvshowmodel.setId(jsonObject1.getString("id"));
                        list.add(searchtvshowmodel);

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(MorethreeActivity.this, RecyclerView.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager2);
                        Morethreeadapter morethreeadapter = new Morethreeadapter(MorethreeActivity.this,list);
                        recycle.setAdapter(morethreeadapter);
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
    }
}