package com.apk.movie1.PopularTVShowFragment;

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
import com.apk.movie1.Trendingmovieadapter.TrendingmovieinfoAdapter;
import com.apk.movie1.model.PopularTVinfomodel;
import com.apk.movie1.model.PopularTVshowTrailorModel;
import com.apk.movie1.model.Trendingmovieinfomodel;
import com.apk.movie1.populartvshowadapter.PopularTVshowTrailorAdapter;
import com.apk.movie1.populartvshowadapter.PopularTvinfoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PopularTVinfoFragment extends Fragment {

    RecyclerView recycle,recycle1;
    TextView text1,text2,text3,text4;
    RequestQueue queue;
    StringRequest request;
    String url;
    List<PopularTVinfomodel> list;
    String persion_id;


    public PopularTVinfoFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_popular_t_vinfo, container, false);

        text1=view.findViewById(R.id.text1);
        text2=view.findViewById(R.id.text2);
        text3=view.findViewById(R.id.text3);
        text4=view.findViewById(R.id.text4);
        recycle=view.findViewById(R.id.recycle);
        recycle1=view.findViewById(R.id.recycle1);

        Trailor();
        url="https://api.themoviedb.org/3/tv/"+persion_id;
        list=new ArrayList<>();

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("fbnbv", "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    text1.setText(jsonObject.getString("first_air_date"));
                    text4.setText(jsonObject.getString("overview"));


                    JSONArray jsonArray=jsonObject.getJSONArray("created_by");

                    for (int i=0; i<jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        text3.setText(jsonObject1.getString("name"));

                        JSONArray jsonArray1=jsonObject.getJSONArray("genres");
                        for (int j=0; j<jsonArray1.length(); j++){

                            JSONObject jsonObject2=jsonArray1.getJSONObject(j);

                            PopularTVinfomodel popularTVinfomodel=new PopularTVinfomodel();

                            popularTVinfomodel.setName(jsonObject2.getString("name"));

                            list.add(popularTVinfomodel);

                            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
                            recycle.setLayoutManager(linearLayoutManager);

                            PopularTvinfoAdapter popularTvinfoAdapter=new PopularTvinfoAdapter(getContext(),list);
                            recycle.setAdapter(popularTvinfoAdapter);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("dfgh", "onErrorResponse: "+error);
            }
        });
        queue.add(request);

        return view;
    }

    List<PopularTVshowTrailorModel>list1;
    String url1;
    public  void Trailor(){

        list1=new ArrayList<>();
        url1="https://api.themoviedb.org/3/tv/"+persion_id+"/videos";

        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url1 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("hgfdsdf", "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        PopularTVshowTrailorModel popularTVshowTrailorModel=new PopularTVshowTrailorModel();
                        popularTVshowTrailorModel.setKey(jsonObject1.getString("key"));
                        list1.add(popularTVshowTrailorModel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle1.setLayoutManager(linearLayoutManager);

                        PopularTVshowTrailorAdapter popularTVshowTrailorAdapter=new PopularTVshowTrailorAdapter(getContext(),list1);
                        recycle1.setAdapter(popularTVshowTrailorAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("gfd", "onErrorResponse: " + error);
            }

        });
        queue.add(request);
    }
}