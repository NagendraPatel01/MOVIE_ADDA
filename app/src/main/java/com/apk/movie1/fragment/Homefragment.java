package com.apk.movie1.fragment;

import android.content.Intent;
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
import com.apk.movie1.MoreoneActivity;
import com.apk.movie1.MorethreeActivity;
import com.apk.movie1.MoretwoActivity;
import com.apk.movie1.TrendingmovieActivity;
import com.apk.movie1.homeadapter.HomeAdapter;
import com.apk.movie1.homeadapter.Homepopularmovieadapter;
import com.apk.movie1.homeadapter.Homepopulartvshowadapter;
import com.apk.movie1.homeadapter.Hometrendingmovieadapter;
import com.apk.movie1.homeadapter.Hometrendingtvshowadapter;
import com.apk.movie1.Adapter.SliderAdapterExample;
import com.apk.movie1.R;
import com.apk.movie1.model.Populartvshowmodel;
import com.apk.movie1.model.Populatmoviemodel;
import com.apk.movie1.model.Trendingmoviemodel;
import com.apk.movie1.model.Trendingpersonmodel;
import com.apk.movie1.model.Trendingtvshowmodel;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Homefragment extends Fragment {

    private static final String TAG = "Homefragment";

    RecyclerView recycle, recycle1, recycle2, recycle3, recycle4;
    SliderView slider;

    TextView more2,more3,more1;

    String url = "https://api.themoviedb.org/3/trending/person/day";
    RequestQueue queue;
    StringRequest request;
    List<Trendingpersonmodel> list;

   /* String url = "https://api.themoviedb.org/3/trending/person/day";
    RequestQueue queue;
    StringRequest request;
    List<Trendingpersonmodel> list;*/
   



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        more2 = view.findViewById(R.id.more2);
        more1 = view.findViewById(R.id.more1);
        more3 = view.findViewById(R.id.more3);
        slider = view.findViewById(R.id.slider);
        recycle = view.findViewById(R.id.recycle);
        recycle1 = view.findViewById(R.id.recycle1);
        recycle2 = view.findViewById(R.id.recycle2);
        recycle3 = view.findViewById(R.id.recycle3);
        recycle4 = view.findViewById(R.id.recycle4);
        list = new ArrayList<>();



        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getContext(), MoreoneActivity.class);
                startActivity(intent);

            }
        });

        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getContext(), MoretwoActivity.class);
                startActivity(intent);

            }
        });

        more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getContext(), MorethreeActivity.class);
                startActivity(intent);

            }
        });


       /* recycle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), TrendingmovieActivity.class);
                startActivity(intent);
            }
        });*/
/*

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recycle.setLayoutManager(linearLayoutManager);
        HomeAdapter adapter = new HomeAdapter(getContext(),list);
        recycle.setAdapter(adapter);
*/
        getPersonData();
        Trendingmovie();
        Trendingtvshow();
        Popularmovie();
        Populartvshow();
        Slider();

        return view;
    }

    public void getPersonData() {


        queue = Volley.newRequestQueue(getContext());
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

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        recycle.setLayoutManager(linearLayoutManager);
                        HomeAdapter adapter = new HomeAdapter(getContext(), list);
                        recycle.setAdapter(adapter);


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

    String url1="https://api.themoviedb.org/3/trending/movie/week";
    List<Trendingmoviemodel>list1;

    public void Trendingmovie(){

        list1 = new ArrayList<>();

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

                     Trendingmoviemodel trendingmoviemodel=new Trendingmoviemodel();

                     if (jsonObject1.has("poster_path") && !jsonObject1.isNull("poster_path")) {
                         trendingmoviemodel.setPoster_path(jsonObject1.getString("poster_path"));
                     }

                     if (jsonObject1.has("title") && !jsonObject1.isNull("title")) {
                         trendingmoviemodel.setTitle(jsonObject1.getString("title"));
                     }
                     trendingmoviemodel.setId(jsonObject1.getString("id"));
                     list1.add(trendingmoviemodel);


                     LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                     recycle1.setLayoutManager(linearLayoutManager1);
                     Hometrendingmovieadapter hometrendingmovieadapter = new Hometrendingmovieadapter(getContext(),list1);
                     recycle1.setAdapter(hometrendingmovieadapter);

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

    String url2="https://api.themoviedb.org/3/trending/tv/week";
    List<Trendingtvshowmodel>list2;

    void Trendingtvshow(){

        list2=new ArrayList<>();

        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url2 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Trendingtvshowmodel trendingtvshowmodel=new Trendingtvshowmodel();
                        trendingtvshowmodel.setName(jsonObject1.getString("name"));
                        trendingtvshowmodel.setPoster_path(jsonObject1.getString("poster_path"));
                        trendingtvshowmodel.setId(jsonObject1.getString("id"));
                        list2.add(trendingtvshowmodel);

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        recycle2.setLayoutManager(linearLayoutManager2);
                        Hometrendingtvshowadapter hometrendingtvshowadapter2 = new Hometrendingtvshowadapter(getContext(),list2);
                        recycle2.setAdapter(hometrendingtvshowadapter2);
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

    String url3="https://api.themoviedb.org/3/movie/popular";
    List<Populatmoviemodel>list3;

    void Popularmovie(){
        list3=new ArrayList<>();

        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url3 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Populatmoviemodel populatmoviemodel=new Populatmoviemodel();
                        populatmoviemodel.setTitle(jsonObject1.getString("title"));
                        populatmoviemodel.setPoster_path(jsonObject1.getString("poster_path"));
                        populatmoviemodel.setId(jsonObject1.getString("id"));
                        list3.add(populatmoviemodel);

                        LinearLayoutManager linearLayoutManage3 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        recycle3.setLayoutManager(linearLayoutManage3);
                        Homepopularmovieadapter homepopularmovieadapter3 = new Homepopularmovieadapter(getContext(),list3);
                        recycle3.setAdapter(homepopularmovieadapter3);


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
    }

    String url4="https://api.themoviedb.org/3/tv/popular";
    List<Populartvshowmodel>list4;
    void Populartvshow(){

        list4=new ArrayList<>();

        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url4 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Populartvshowmodel populartvshowmodel=new Populartvshowmodel();

                        populartvshowmodel.setName(jsonObject1.getString("name"));
                        populartvshowmodel.setBackdrop_path(jsonObject1.getString("backdrop_path"));
                        populartvshowmodel.setId(jsonObject1.getString("id"));
                        list4.add(populartvshowmodel);



                        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        recycle4.setLayoutManager(linearLayoutManager4);

                        Homepopulartvshowadapter homepopulartvshowadapter4 = new Homepopulartvshowadapter(getContext(),list4);
                        recycle4.setAdapter(homepopulartvshowadapter4);

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
    String url5="https://api.themoviedb.org/3/trending/movie/week";
    List<Trendingmoviemodel>list5;

    void Slider(){
        list5=new ArrayList<>();

        queue=Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url5 + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){


                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        Trendingmoviemodel trendingmoviemodel=new Trendingmoviemodel();
                        trendingmoviemodel.setPoster_path(jsonObject1.getString("poster_path"));
                        trendingmoviemodel.setId(jsonObject1.getString("id"));
                        list5.add(trendingmoviemodel);

                        slider.setSliderAdapter(new SliderAdapterExample(getContext(),list5));

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