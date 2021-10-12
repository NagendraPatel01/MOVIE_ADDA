package com.apk.movie1.searchmoviefragment;

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
import com.apk.movie1.Adapter.searchadapter.Searchmovieinfoadapter;
import com.apk.movie1.Adapter.searchadapter.Searchmovietrailoradapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Searchmovieinfomodel;
import com.apk.movie1.model.Searchmovietrailormodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SearchmovieinfoFragment extends Fragment {

    private static final String TAG = "SearchmovieinfoFragment";
    TextView text1,text2,text3,text4;
    StringRequest request;
    RequestQueue queue;
    RecyclerView recycle,recycle1;
    String url;
    String persion_id;
    List<Searchmovieinfomodel>list;


    public SearchmovieinfoFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_searchmovieinfo, container, false);
        text1=view.findViewById(R.id.text1);
        text2=view.findViewById(R.id.text2);
        text3=view.findViewById(R.id.text3);
        text4=view.findViewById(R.id.text4);
        recycle=view.findViewById(R.id.recycle);
        recycle1=view.findViewById(R.id.recycle1);
        list=new ArrayList<>();

        Trailor();

        url= "https://api.themoviedb.org/3/movie/"+persion_id;
        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    text1.setText(jsonObject.getString("release_date"));
                    text2.setText(jsonObject.getString("runtime"));
                    text3.setText(jsonObject.getString("original_title"));
                    text4.setText(jsonObject.getString("overview"));

                    JSONArray jsonArray=jsonObject.getJSONArray("genres");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject  jsonObject1=jsonArray.getJSONObject(i);

                        Searchmovieinfomodel searchmovieinfomodel=new Searchmovieinfomodel();
                        searchmovieinfomodel.setName(jsonObject1.getString("name"));
                        list.add(searchmovieinfomodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Searchmovieinfoadapter searchmovieinfoadapter=new Searchmovieinfoadapter(getContext(),list);
                        recycle.setAdapter(searchmovieinfoadapter);

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

    String url1;
    List<Searchmovietrailormodel>list1;
    public  void Trailor(){

        url1="https://api.themoviedb.org/3/movie/"+persion_id+"/videos";
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

                        Searchmovietrailormodel searchmovietrailormodel=new Searchmovietrailormodel();
                        searchmovietrailormodel.setKey(jsonObject1.getString("key"));
                        list1.add(searchmovietrailormodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle1.setLayoutManager(linearLayoutManager);

                        Searchmovietrailoradapter searchmovietrailoradapter=new Searchmovietrailoradapter(getContext(),list1);
                        recycle1.setAdapter(searchmovietrailoradapter);

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