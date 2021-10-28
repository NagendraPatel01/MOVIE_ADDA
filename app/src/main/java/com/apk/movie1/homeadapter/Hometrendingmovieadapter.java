package com.apk.movie1.homeadapter;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.TrendingmovieActivity;
import com.apk.movie1.model.Trendingmoviemodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Hometrendingmovieadapter extends RecyclerView.Adapter<Hometrendingmovieadapter.HometrendinViewHolder> {

    Context context;
    List<Trendingmoviemodel>list1;

    public Hometrendingmovieadapter(Context context, List<Trendingmoviemodel> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public HometrendinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recycle2,parent,false);
        return new HometrendinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HometrendinViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.text.setText(list1.get(position).getTitle());
        Log.d("kjhgfdfghj", "onBindViewHolder: "+list1.get(position).getTitle());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list1.get(position).getPoster_path()).into(holder.img);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, TrendingmovieActivity.class);
                intent.putExtra("persion_id",list1.get(position).getId());
                Log.d("dfrtgyhu", "rtyuikytrtyhj: "+list1.get(position).getId());


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }


    public class HometrendinViewHolder extends RecyclerView.ViewHolder{

          CardView cardView;
          TextView text;
          ImageView img;
        public HometrendinViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.cardview);
            text=itemView.findViewById(R.id.text);
            img=itemView.findViewById(R.id.img);
        }
    }
}
