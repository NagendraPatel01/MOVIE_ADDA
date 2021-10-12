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
import com.apk.movie1.homeadapter.HomeAdapter;
import com.apk.movie1.model.Trendingpersonmodel;
import com.apk.movie1.moreadapter.Moreoneadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoreoneActivity extends AppCompatActivity {

    private static final String TAG = "Homefragment";

    String url = "https://api.themoviedb.org/3/trending/person/day";
    RequestQueue queue;
    StringRequest request;
    List<Trendingpersonmodel> list;
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreone);

        recycle = findViewById(R.id.recycle);
        list = new ArrayList<>();


        queue = Volley.newRequestQueue(MoreoneActivity.this);
        request = new StringRequest(Request.Method.GET, url+"?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Trendingpersonmodel trendingpersonmodel = new Trendingpersonmodel();

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        if (jsonObject1.has("name") && !jsonObject1.isNull("name")) {
                            trendingpersonmodel.setName(jsonObject1.getString("name"));
                            trendingpersonmodel.setId(jsonObject1.getString("id"));
                        }

                        if (jsonObject1.has("profile_path") &&  !jsonObject1.isNull("profile_path")){
                            Log.d(TAG, "onResponse11: "+jsonObject1.getString("profile_path"));
                            trendingpersonmodel.setProfile_path(jsonObject1.getString("profile_path"));
                        }


                        if (jsonObject1.has("known_for") && !jsonObject1.isNull("known_for")) {
                            JSONArray jsonArray1 = jsonObject1.getJSONArray("known_for");
                            for (int i1 = 0; i1 < jsonArray1.length(); i1++) {


                            }

                        }
                        list.add(trendingpersonmodel);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MoreoneActivity.this, RecyclerView.VERTICAL, false);
                        recycle.setLayoutManager(linearLayoutManager);
                        Moreoneadapter moreoneadapter = new Moreoneadapter(MoreoneActivity.this, list);
                        recycle.setAdapter(moreoneadapter);


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

    }
}