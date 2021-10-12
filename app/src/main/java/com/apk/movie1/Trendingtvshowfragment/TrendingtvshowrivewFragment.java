package com.apk.movie1.Trendingtvshowfragment;

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

public class TrendingtvshowrivewFragment extends Fragment {

    private static final String TAG = "TrendingtvshowrivewFrag";
    TextView text1,text2;
    RequestQueue queue;
    StringRequest request;
    String url;
    String persion_id;

    public TrendingtvshowrivewFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trendingtvshowrivew, container, false);

        text1=view.findViewById(R.id.text1);
        text2=view.findViewById(R.id.text2);

        url="https://api.themoviedb.org/3/tv/"+persion_id+"/reviews";

        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);


                        if (jsonObject1.has("author") && !jsonObject1.isNull("author")) {
                            text1.setText(jsonObject1.getString("author"));
                        }

                        if (jsonObject1.has("content") && !jsonObject1.isNull("content")) {
                            text2.setText(jsonObject1.getString("content"));
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
}