package com.apk.movie1.popularmoviefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.movie1.Adapter.Popularmovieinfoadapter;
import com.apk.movie1.Adapter.Popularmovietrailoradapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Popularmovieinfomodel;
import com.apk.movie1.model.popularmovietrailormodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PopularmovieinfoFragment extends Fragment {

    private static final String TAG = "PopularmovieinfoFragmen";
     TextView text1,text2,text3,text4;
     RecyclerView recycle,recycle1;
     RequestQueue queue;
     StringRequest request;
     String url;
     String persion_id;
     List<Popularmovieinfomodel>list;

    public PopularmovieinfoFragment(String persion_id1) {
        this.persion_id = persion_id1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_popularmovieinfo, container, false);


        url= "https://api.themoviedb.org/3/movie/"+persion_id;
        list=new ArrayList<>();

        text1=view.findViewById(R.id.text1);
        text2=view.findViewById(R.id.text2);
        text3=view.findViewById(R.id.text3);
        text4=view.findViewById(R.id.text4);
        recycle=view.findViewById(R.id.recycle);
        recycle1=view.findViewById(R.id.recycle1);

        Trailor();

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse23: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("genres");

                    if (jsonObject.has("release_date") && !jsonObject.isNull("release_date")) {
                        text1.setText(jsonObject.getString("release_date"));
                    }

                    if (jsonObject.has("runtime") && !jsonObject.isNull("runtime")) {
                        text2.setText(jsonObject.getString("runtime"));
                    }

                    if (jsonObject.has("original_title") && !jsonObject.isNull("original_title")) {
                        text3.setText(jsonObject.getString("original_title"));
                    }

                    if (jsonObject.has("overview") && !jsonObject.isNull("overview")) {
                        text4.setText(jsonObject.getString("overview"));
                    }

                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Popularmovieinfomodel popularmovieinfomodel=new Popularmovieinfomodel();
                        popularmovieinfomodel.setName(jsonObject1.getString("name"));
                        list.add(popularmovieinfomodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Popularmovieinfoadapter popularmovieinfoadapter=new Popularmovieinfoadapter(getContext(),list);
                        recycle.setAdapter(popularmovieinfoadapter);
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

    String url1;
    List<popularmovietrailormodel>list1;
    public  void Trailor(){

        url1= "https://api.themoviedb.org/3/movie/ "+persion_id+",/videos";
        list1=new ArrayList<>();
        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url1 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        popularmovietrailormodel popularmovietrailormodel=new popularmovietrailormodel();
                        popularmovietrailormodel.setKey(jsonObject1.getString("key"));
                        list1.add(popularmovietrailormodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle1.setLayoutManager(linearLayoutManager);

                        Popularmovietrailoradapter popularmovietrailoradapter=new Popularmovietrailoradapter(getContext(),list1);
                        recycle1.setAdapter(popularmovietrailoradapter);


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