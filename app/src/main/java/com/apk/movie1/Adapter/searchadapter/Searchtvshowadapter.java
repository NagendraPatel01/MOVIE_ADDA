package com.apk.movie1.Adapter.searchadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.Adapter.Popularmovieinfoadapter;
import com.apk.movie1.R;
import com.apk.movie1.SearchmovieoneActivity;
import com.apk.movie1.SearchtvshowoneActivity;
import com.apk.movie1.model.Searchtvshowmodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Searchtvshowadapter extends RecyclerView.Adapter<Searchtvshowadapter.ViewHolder>{

    Context context;
    List<Searchtvshowmodel>list;

    public Searchtvshowadapter(Context context, List<Searchtvshowmodel> list) {
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

        holder.text1.setText(list.get(position).getFirst_air_date());
        holder.text2.setText(list.get(position).getName());
        holder.text3.setText(list.get(position).getVote_average());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list.get(position).getPoster_path()).into(holder.img);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,SearchtvshowoneActivity.class);
                intent.putExtra("persion_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardview;
        ImageView img;
        TextView text1,text2,text3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview=itemView.findViewById(R.id.cardview);
            img=itemView.findViewById(R.id.img);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);


        }
    }

}
