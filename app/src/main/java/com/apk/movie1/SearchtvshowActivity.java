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
import com.apk.movie1.Adapter.searchadapter.Searchtvshowadapter;
import com.apk.movie1.model.Searchtvshowmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchtvshowActivity extends AppCompatActivity {

    private static final String TAG = "SearchtvshowActivity";
    RecyclerView recycle;
    RequestQueue queue;
    StringRequest request;
    String url="https://api.themoviedb.org/3/trending/tv/week";
    List<Searchtvshowmodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchtvshow);

        list=new ArrayList<>();
        recycle=findViewById(R.id.recycle);
        queue= Volley.newRequestQueue(SearchtvshowActivity.this);
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
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

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SearchtvshowActivity.this,RecyclerView.VERTICAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Searchtvshowadapter searchtvshowadapter=new Searchtvshowadapter(SearchtvshowActivity.this,list);
                        recycle.setAdapter(searchtvshowadapter);
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