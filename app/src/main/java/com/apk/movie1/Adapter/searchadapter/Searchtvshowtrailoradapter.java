package com.apk.movie1.Adapter.searchadapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.model.Searchtvshowtrailormodel;
import com.bumptech.glide.Glide;

import java.util.List;

public  class Searchtvshowtrailoradapter extends RecyclerView.Adapter<Searchtvshowtrailoradapter.ViewHolder> {
    Context context;
    List<Searchtvshowtrailormodel>list1;

    public Searchtvshowtrailoradapter(Context context, List<Searchtvshowtrailormodel> list1) {
        this.context = context;
        this.list1 = list1;
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

        Glide.with(context).load("https://img.youtube.com/vi/"+list1.get(position).getKey()+"/sddefault.jpg").into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+list1.get(position).getKey()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
        }
    }
}
