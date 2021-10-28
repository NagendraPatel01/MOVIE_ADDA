package com.apk.movie1.Trendinpersonfrgment;

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
import com.apk.movie1.R;
import com.apk.movie1.Trendingmoviefragment.Trendingmovietrailoradapter;
import com.apk.movie1.model.Popularmovieinfomodel;
import com.apk.movie1.model.Trendingmovietrailormodel;
import com.apk.movie1.model.TrendingpersionmovieoneModel;
import com.apk.movie1.model.TrendingpersonmovieonetrailerModel;
import com.apk.movie1.trendingpersonadapter.TrendingpersonmovieonetrailerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrendingPersonMovieoneFragment extends Fragment {

    private static final String TAG = "PopularmovieinfoFragmen";
    TextView text1,text2,text3;
    RecyclerView recycle1;
    RequestQueue queue;
    StringRequest request;
    String url;
    List<TrendingpersionmovieoneModel> list;


    String persion_id;

    public TrendingPersonMovieoneFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trending_person_movieone, container, false);

        url = "https://api.themoviedb.org/3/movie/"+persion_id;
        list=new ArrayList<>();

        text1=view.findViewById(R.id.text1);
        text2=view.findViewById(R.id.text2);
        text3=view.findViewById(R.id.text3);
        recycle1=view.findViewById(R.id.recycle1);

        Trailor();

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "fghjhgfdfghjkjhghj "+response);


                try {
                    JSONObject jsonObject=new JSONObject(response);


                        text1.setText(jsonObject.getString("release_date"));
                        text2.setText(jsonObject.getString("original_title"));
                        text3.setText(jsonObject.getString("overview"));

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
    List<TrendingpersonmovieonetrailerModel>list1;
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
                    for (int i=1; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        TrendingpersonmovieonetrailerModel
                                trendingpersonmovieonetrailerModel=new TrendingpersonmovieonetrailerModel();
                        trendingpersonmovieonetrailerModel.setKey(jsonObject1.getString("key"));
                        list1.add(trendingpersonmovieonetrailerModel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle1.setLayoutManager(linearLayoutManager);

                        TrendingpersonmovieonetrailerAdapter trendingpersonmovieonetrailerAdapter=new TrendingpersonmovieonetrailerAdapter(getContext(),list1);
                        recycle1.setAdapter(trendingpersonmovieonetrailerAdapter);
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