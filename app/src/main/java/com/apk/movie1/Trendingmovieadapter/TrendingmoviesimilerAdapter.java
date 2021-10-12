package com.apk.movie1.Trendingmovieadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.apk.movie1.R;
import com.apk.movie1.model.Trendingmoviesimilermodel;
import com.bumptech.glide.Glide;
import java.util.List;

public class TrendingmoviesimilerAdapter extends RecyclerView.Adapter<TrendingmoviesimilerAdapter.ViewHolder> {

    private static final String TAG = "TrendingmoviesimilerAda";
    Context context;
    List<Trendingmoviesimilermodel>list;

    public TrendingmoviesimilerAdapter(Context context, List<Trendingmoviesimilermodel> list) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text1.setText(list.get(position).getRelease_date());

        Log.d(TAG, "onBindViewHolder: "+list);
        //Log.d(TAG, "onBindViewHolder: "+list.get(position).getRelease_date());
      //  Log.d(TAG, "onBindViewHolder: "+list.get(position).getTitle());
        holder.text2.setText(list.get(position).getTitle());
        holder.text3.setText(list.get(position).getVote_average());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list.get(position).getPoster_path()).into(holder.img);
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

            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);
            img=itemView.findViewById(R.id.img);
        }
    }
}
