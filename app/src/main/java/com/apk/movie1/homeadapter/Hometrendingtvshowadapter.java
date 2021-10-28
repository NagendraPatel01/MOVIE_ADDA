package com.apk.movie1.homeadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.TrendingtvshowActivity;
import com.apk.movie1.model.Trendingtvshowmodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Hometrendingtvshowadapter extends RecyclerView.Adapter<Hometrendingtvshowadapter.ViewHolder> {

    Context context;
    List<Trendingtvshowmodel>list2;

    public Hometrendingtvshowadapter(Context context, List<Trendingtvshowmodel> list2) {
        this.context = context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recycle2,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text.setText(list2.get(position).getName());
         //https://image.tmdb.org/t/p/w500
        //https://image.tmdb.org/t/p/w185
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list2.get(position).getPoster_path()).into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, TrendingtvshowActivity.class);
                intent.putExtra("persion_id",list2.get(position).getId());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list2.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView img;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.cardview);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
        }
    }
}
