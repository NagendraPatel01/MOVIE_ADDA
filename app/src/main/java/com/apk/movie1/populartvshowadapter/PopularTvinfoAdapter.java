package com.apk.movie1.populartvshowadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.movie1.R;
import com.apk.movie1.model.PopularTVinfomodel;

import java.util.List;

public class PopularTvinfoAdapter  extends RecyclerView.Adapter<PopularTvinfoAdapter.ViewHolder> {

    Context context;
    List<PopularTVinfomodel>list;

    public PopularTvinfoAdapter(Context context, List<PopularTVinfomodel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.gener,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder( PopularTvinfoAdapter.ViewHolder holder, int position) {

        holder.text5.setText(list.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {

        TextView text5;
        public ViewHolder( View itemView) {
            super(itemView);

            text5=itemView.findViewById(R.id.text5);
        }
    }
}
