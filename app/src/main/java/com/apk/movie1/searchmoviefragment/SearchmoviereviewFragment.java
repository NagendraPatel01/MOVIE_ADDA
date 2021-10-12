package com.apk.movie1.searchmoviefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class SearchmoviereviewFragment extends Fragment {
    private static final String TAG = "SearchmoviereviewFragme";
    RequestQueue queue;
    StringRequest request;
    String url;
    TextView text,text1;
    String persion_id;


    public SearchmoviereviewFragment(String persion_id) {
        this.persion_id = persion_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_searchmoviereview, container, false);

        text=view.findViewById(R.id.text);
        text1=view.findViewById(R.id.text1);

        url= "https://api.themoviedb.org/3/movie/"+persion_id+"/reviews";
        queue= Volley.newRequestQueue(getContext());
        request=new StringRequest(Request.Method.GET, url + "?api_key=c4824776bf6f08433a4c4e7cd75a6acc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray =jsonObject.getJSONArray("results");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        text.setText(jsonObject1.getString("author"));
                        text1.setText(jsonObject1.getString("content"));
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
}