package com.apk.movie1.populartvshowadapter;

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
import com.apk.movie1.model.PopularTVshowTrailorModel;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class PopularTVshowTrailorAdapter extends RecyclerView.Adapter<PopularTVshowTrailorAdapter.ViewHolder>{

    Context context;
    List<PopularTVshowTrailorModel>list;

    public PopularTVshowTrailorAdapter(Context context, List<PopularTVshowTrailorModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.trailot,parent,false);
        return new  ViewHolder(view);
    }


    @Override
    public void onBindViewHolder( PopularTVshowTrailorAdapter.ViewHolder holder, int position) {

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+list.get(position).getKey()));
                context.startActivity(intent);
            }
        });

        Glide.with(context).load("https://img.youtube.com/vi/"+list.get(position).getKey()+"/sddefault.jpg").into(holder.img);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ViewHolder( View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
        }
    }
}
