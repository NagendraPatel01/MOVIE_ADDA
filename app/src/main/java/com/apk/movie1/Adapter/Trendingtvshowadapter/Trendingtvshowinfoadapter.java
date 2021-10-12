package com.apk.movie1.Adapter.Trendingtvshowadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.model.Trendingtvshowinfomodel;

import java.util.List;

public class Trendingtvshowinfoadapter extends RecyclerView.Adapter<Trendingtvshowinfoadapter.ViewHolder> {

    Context context;
    List<Trendingtvshowinfomodel>list;

    public Trendingtvshowinfoadapter(Context context, List<Trendingtvshowinfomodel> list) {
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
