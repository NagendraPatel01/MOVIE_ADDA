package com.apk.movie1.trendingpersonadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.TrendingpersonMovieActivity;
import com.apk.movie1.model.Trendingpersonmoviemodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class TrendingpersonmovieAdapter extends RecyclerView.Adapter<TrendingpersonmovieAdapter.ViewHolder> {
    private static final String TAG = "TrendingpersonmovieAdap";
    Context context;
    List<Trendingpersonmoviemodel>list;


    public TrendingpersonmovieAdapter(Context context, List<Trendingpersonmoviemodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.searchmovierecycle,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text1.setText(list.get(position).getRelease_date());
        Log.d(TAG, "onBindViewHolder: "+position);
        holder.text2.setText(list.get(position).getTitle());
        holder.text3.setText(list.get(position).getVote_average());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list.get(position).getBackdrop_path() ).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, TrendingpersonMovieActivity.class);
                intent.putExtra("persion_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text1,text2,text3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);
        }
    }
}
