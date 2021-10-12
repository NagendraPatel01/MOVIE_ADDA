package com.apk.movie1.PopularTVShowFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PopularTvreviewFragment extends Fragment {

    TextView text,text1;
    String url;
    RequestQueue queue;
    StringRequest request;
    String persion_id;

    public PopularTvreviewFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_tvreview, container, false);

        text=view.findViewById(R.id.text);
        text1=view.findViewById(R.id.text1);

        url="https://api.themoviedb.org/3/tv/"+persion_id+"/reviews";

        queue= Volley.newRequestQueue(getContext());

        request= new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("dfghgf", "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        // Trendingmoviereviewmodel trendingmoviereviewmodel=new Trendingmoviereviewmodel();

                        if (jsonObject1.has("author") && !jsonObject1.isNull("author")) {
                            text.setText( jsonObject1.getString("author"));
                        }

                        if (jsonObject1.has("content") && !jsonObject1.isNull("content")) {

                            text1.setText( jsonObject1.getString("content"));
                        }

                        //  list.add(trendingmoviereviewmodel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fghgf", "onErrorResponse: "+error);

            }
        });
        queue.add(request);
        return view;
    }
}