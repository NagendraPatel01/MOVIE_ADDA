package com.apk.movie1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.TrendingmovieActivity;
import com.apk.movie1.model.Trendingmoviemodel;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderViewHolder> {

    Context context;
    List <Trendingmoviemodel>list5;

    public SliderAdapterExample(Context context, List<Trendingmoviemodel> list5) {
        this.context = context;
        this.list5 = list5;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.imageslider,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        //https://image.tmdb.org/t/p/original
        //https://image.tmdb.org/t/p/w185
        //https://image.tmdb.org/t/p/w500

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context, TrendingmovieActivity.class);
                intent.putExtra("persion_id",list5.get(position).getId());
                context.startActivity(intent);
            }
        });

        Glide.with(context).load("https://image.tmdb.org/t/p/original"+list5.get(position).getPoster_path()).into(viewHolder.img);

    }


    @Override
    public int getCount() {
        return list5.size();
    }

    class SliderViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView img;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
        }
    }
}
