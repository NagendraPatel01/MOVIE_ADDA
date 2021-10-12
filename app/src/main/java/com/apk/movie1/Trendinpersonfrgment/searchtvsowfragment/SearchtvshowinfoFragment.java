package com.apk.movie1.Trendinpersonfrgment.searchtvsowfragment;

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
import com.apk.movie1.Adapter.searchadapter.Searchtvshowinfoadapter;
import com.apk.movie1.Adapter.searchadapter.Searchtvshowtrailoradapter;
import com.apk.movie1.R;
import com.apk.movie1.model.Searchtvshowinfomodel;
import com.apk.movie1.model.Searchtvshowtrailormodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchtvshowinfoFragment extends Fragment {

    private static final String TAG = "SearchtvshowinfoFragmen";
    RequestQueue queue;
    StringRequest request;
    String  persion_id;
    String url;
    TextView text1,text3,text4;
    RecyclerView recycle,recycle1;
    List<Searchtvshowinfomodel>list;

    public SearchtvshowinfoFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_searchtvshow, container, false);

        text1=view.findViewById(R.id.text1);
        text3=view.findViewById(R.id.text3);
        text4=view.findViewById(R.id.text4);
        recycle1=view.findViewById(R.id.recycle1);
        recycle=view.findViewById(R.id.recycle);

        trailor();
        url= "https://api.themoviedb.org/3/tv/"+persion_id;
        list= new ArrayList<>();

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    text1.setText(jsonObject.getString("first_air_date"));
                    text4.setText(jsonObject.getString("overview"));
                    text3.setText(jsonObject.getString("name"));

                    JSONArray jsonArray=jsonObject.getJSONArray("genres");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        Searchtvshowinfomodel searchtvshowinfomodel=new Searchtvshowinfomodel();
                        searchtvshowinfomodel.setName(jsonObject1.getString("name"));
                        list.add(searchtvshowinfomodel);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                        recycle.setLayoutManager(linearLayoutManager);

                        Searchtvshowinfoadapter searchtvshowinfoadapter=new Searchtvshowinfoadapter(getContext(),list);
                        recycle.setAdapter(searchtvshowinfoadapter);


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
    List<Searchtvshowtrailormodel>list1;
  public   void trailor(){

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

                      Searchtvshowtrailormodel searchtvshowtrailormodel=new Searchtvshowtrailormodel();
                     searchtvshowtrailormodel.setKey(jsonObject1.getString("key"));
                     list1.add(searchtvshowtrailormodel);

                      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
                      recycle1.setLayoutManager(linearLayoutManager);

                      Searchtvshowtrailoradapter searchtvshowtrailoradapter=new Searchtvshowtrailoradapter(getContext(),list1);
                      recycle1.setAdapter(searchtvshowtrailoradapter);

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
}