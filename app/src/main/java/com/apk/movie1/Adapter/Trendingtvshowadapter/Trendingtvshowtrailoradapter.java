package com.apk.movie1.Adapter.Trendingtvshowadapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.model.Trendingtvshowtrailormodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Trendingtvshowtrailoradapter extends RecyclerView.Adapter<Trendingtvshowtrailoradapter.ViewHolder> {

    Context context;
    List<Trendingtvshowtrailormodel>list1;

    public Trendingtvshowtrailoradapter(Context context, List<Trendingtvshowtrailormodel> list) {
        this.context = context;
        this.list1 = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.trailot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+list1.get(position).getKey()));
                context.startActivity(intent);
            }
        });

        Glide.with(context).load("https://img.youtube.com/vi/"+list1.get(position).getKey()+"/sddefault.jpg").into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
        }
    }
}
