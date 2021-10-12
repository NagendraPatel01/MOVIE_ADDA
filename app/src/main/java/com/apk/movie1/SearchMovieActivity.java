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
import com.apk.movie1.Adapter.searchadapter.Searchmovieadapter;
import com.apk.movie1.model.Searchmoviemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {

    private static final String TAG = "SearchMovieActivity";
    RecyclerView recycle;
    RequestQueue queue;
    StringRequest request;
    List<Searchmoviemodel>list;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        recycle=findViewById(R.id.recycle);
        list=new ArrayList<>();
        url="https://api.themoviedb.org/3/trending/movie/week";

        queue= Volley.newRequestQueue(SearchMovieActivity.this);
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
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

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SearchMovieActivity.this,RecyclerView.VERTICAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Searchmovieadapter searchmovieadapter=new Searchmovieadapter(SearchMovieActivity.this,list);
                        recycle.setAdapter(searchmovieadapter);

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