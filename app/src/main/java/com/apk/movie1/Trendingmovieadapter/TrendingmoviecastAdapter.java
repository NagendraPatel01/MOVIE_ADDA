package com.apk.movie1.Trendingmovieadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrendingmoviecastAdapter extends RecyclerView.Adapter<TrendingmoviecastAdapter.ViewHolder> {

    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recycle4,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView text,text1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
            text1=itemView.findViewById(R.id.text1);
        }
    }
}
