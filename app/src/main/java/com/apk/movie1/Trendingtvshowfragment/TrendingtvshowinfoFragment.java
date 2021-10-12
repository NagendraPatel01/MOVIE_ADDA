package com.apk.movie1.Trendingtvshowfragment;

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
import com.apk.movie1.Adapter.Trendingtvshowadapter.Trendingtvshowinfoadapter;
import com.apk.movie1.Adapter.Trendingtvshowadapter.Trendingtvshowtrailoradapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingtvshowinfomodel;
import com.apk.movie1.model.Trendingtvshowtrailormodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendingtvshowinfoFragment extends Fragment {

    private static final String TAG = "TrendingtvshowinfoFragm";
    RequestQueue queue;
    StringRequest request;
    String url;
    String persion_id;
    TextView text1,text3,text4;
    List<Trendingtvshowinfomodel>list;
    RecyclerView recycle,recycle1;

    public TrendingtvshowinfoFragment(String persion_id1) {
        this.persion_id = persion_id1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trendingtvshowinfo, container, false);



        url="https://api.themoviedb.org/3/tv/"+persion_id;
        list=new ArrayList<>();
        text1=view.findViewById(R.id.text1);
        text3=view.findViewById(R.id.text3);
        text4=view.findViewById(R.id.text4);
        recycle=view.findViewById(R.id.recycle);
        recycle1=view.findViewById(R.id.recycle1);

        Trailor();

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    text1.setText(jsonObject.getString("first_air_date"));
                    text4.setText(jsonObject.getString("overview"));

                    JSONArray jsonArray=jsonObject.getJSONArray("created_by");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        text3.setText(jsonObject1.getString("name"));

                        JSONArray jsonArray1=jsonObject.getJSONArray("genres");
                        for (int i1=0; i<jsonArray1.length(); i1++){

                            JSONObject jsonObject2=jsonArray1.getJSONObject(i1);


                            Trendingtvshowinfomodel trendingtvshowinfomodel=new Trendingtvshowinfomodel();
                            trendingtvshowinfomodel.setName(jsonObject2.getString("name"));
                            list.add(trendingtvshowinfomodel);

                            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
                            recycle.setLayoutManager(linearLayoutManager);

                            Trendingtvshowinfoadapter trendingtvshowinfoadapter=new Trendingtvshowinfoadapter(getContext(),list);
                            recycle.setAdapter(trendingtvshowinfoadapter);




                        }
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
    List<Trendingtvshowtrailormodel>list1;

   public void Trailor(){

       url1="https://api.themoviedb.org/3/tv/"+persion_id+"/videos";
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

                       Trendingtvshowtrailormodel trendingtvshowtrailormodel=new Trendingtvshowtrailormodel();
                       trendingtvshowtrailormodel.setKey(jsonObject1.getString("key"));
                       list1.add(trendingtvshowtrailormodel);

                       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                       recycle1.setLayoutManager(linearLayoutManager);

                       Trendingtvshowtrailoradapter trendingtvshowtrailoradapter=new Trendingtvshowtrailoradapter(getContext(),list1);
                       recycle1.setAdapter(trendingtvshowtrailoradapter);

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