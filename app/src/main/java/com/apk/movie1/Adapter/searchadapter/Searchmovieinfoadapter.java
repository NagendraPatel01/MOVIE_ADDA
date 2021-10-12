package com.apk.movie1.Adapter.searchadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.model.Searchmovieinfomodel;

import org.w3c.dom.Text;

import java.util.List;

public class Searchmovieinfoadapter extends RecyclerView.Adapter<Searchmovieinfoadapter.VievHolder> {

    Context context;
    List<Searchmovieinfomodel>list;

    public Searchmovieinfoadapter(Context context, List<Searchmovieinfomodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.gener,parent,false);
        return new VievHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull VievHolder holder, int position) {

        holder.text5.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VievHolder extends RecyclerView.ViewHolder{

        TextView text5;

        public VievHolder(@NonNull View itemView) {
            super(itemView);
            text5=itemView.findViewById(R.id.text5);
        }
    }
}
