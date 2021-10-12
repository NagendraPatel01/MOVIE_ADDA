package com.apk.movie1.trendingpersonadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.ImageActivity;
import com.apk.movie1.R;
import com.apk.movie1.SplashActivity;
import com.apk.movie1.Trendinpersonfrgment.TrendingpersoninfoFragment;
import com.apk.movie1.model.Trendingpersoninfomodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class TrendingpersoninfoAdapter extends RecyclerView.Adapter<TrendingpersoninfoAdapter.ViewHolder> {

    Context context;
    List <Trendingpersoninfomodel>list;

    public TrendingpersoninfoAdapter(Context context, List<Trendingpersoninfomodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recycle3,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, ImageActivity.class);
                intent.putExtra("ankit",list.get(position).getProfile_path());
                context.startActivity(intent);

            }
        });

       holder.text1.setText(list.get(position).getBirthday());
       holder.text2.setText(list.get(position).getBirthday());
       holder.text3.setText(list.get(position).getName());
       holder.text4.setText(list.get(position).getBiography());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+list.get(position).getProfile_path()).into(holder.img);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text1,text2,text3,text4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);
            text4=itemView.findViewById(R.id.text4);
        }
    }
}
