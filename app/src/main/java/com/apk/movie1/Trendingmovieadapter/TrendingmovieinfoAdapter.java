package com.apk.movie1.Trendingmovieadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.MainActivity;
import com.apk.movie1.R;
import com.apk.movie1.TrendingmovieActivity;
import com.apk.movie1.homeadapter.HomeAdapter;
import com.apk.movie1.model.Trendingmovieinfomodel;

import java.util.List;

public class TrendingmovieinfoAdapter extends RecyclerView.Adapter<TrendingmovieinfoAdapter.InfoViewHolder> {

    Context context;
    List<Trendingmovieinfomodel>list;

    public TrendingmovieinfoAdapter(Context context, List<Trendingmovieinfomodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.gener,parent,false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {

        holder.text5.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{

        TextView text5;
        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            text5=itemView.findViewById(R.id.text5);
        }
    }
}
