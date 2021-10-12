package com.apk.movie1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.homeadapter.Homepopulartvshowadapter;
import com.apk.movie1.model.Popularmovieinfomodel;

import java.util.List;

public class Popularmovieinfoadapter extends RecyclerView.Adapter<Popularmovieinfoadapter.ViewHolder> {

    Context context;
    List<Popularmovieinfomodel>list;

    public Popularmovieinfoadapter(Context context, List<Popularmovieinfomodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.gener,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text5.setText(list.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView text5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text5=itemView.findViewById(R.id.text5);
        }
    }
}
