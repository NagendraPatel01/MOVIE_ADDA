package com.apk.movie1.moreadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.TrendingpersonActivity;
import com.apk.movie1.homeadapter.HomeAdapter;
import com.apk.movie1.model.Trendingpersonmodel;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Moreoneadapter extends RecyclerView.Adapter<Moreoneadapter.ViewHolder>{

    Context context;
    List<Trendingpersonmodel>list;

    public Moreoneadapter(Context context, List<Trendingpersonmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.searchperson,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text.setText(list.get(position).getName());

        Glide.with(context).load("https://image.tmdb.org/t/p/w185"+list.get(position).getProfile_path()).into(holder.img);



        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, TrendingpersonActivity.class);
                intent.putExtra("person_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relative;
        CircleImageView img;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relative=itemView.findViewById(R.id.relative);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
        }
    }
}
