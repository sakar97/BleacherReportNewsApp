package com.example.sm.gnews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Items> mitems;

    RecyclerViewAdapter(Context context, List<Items> mitems) {
        this.context = context;
        this.mitems = mitems;
    }
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.items,parent,false);
        return new MyViewHolder(view,context,mitems);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.auth.setText(mitems.get(position).getAuthor());
        holder.tit.setText(mitems.get(position).getTitle());
        holder.des.setText(mitems.get(position).getDescription());
        //holder.cont.setText(mitems.get(position).getContent());
        //holder.pub.setText(mitems.get(position).getPublishAt());
        Picasso.with(context).load(mitems.get(position).getUrltoImage()).into(holder.urltoimg);

    }

    @Override
    public int getItemCount() {
        return mitems.size();

    }
    public void removeItem(int position){
        mitems.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Items deletednews, int deletedIndex) {
        mitems.add(deletedIndex, deletednews);
        notifyItemInserted(deletedIndex);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView auth,tit,des,cont,pub;
        ImageView urltoimg;
        List<Items> items=new ArrayList<>();
        Context context;

        MyViewHolder(View itemView,Context context,List<Items> items) {
            super(itemView);
            this.items=items;
            this.context=context;
            itemView.setOnClickListener(this);
            //auth=itemView.findViewById(R.id.author);
            tit=itemView.findViewById(R.id.title);
            des=itemView.findViewById(R.id.description);
            urltoimg=itemView.findViewById(R.id.urlimage);
            //cont=itemView.findViewById(R.id.content);
            //pub=itemView.findViewById(R.id.publishAt);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Items items=this.items.get(position);
            Intent intent=new Intent(this.context,News.class);
            intent.putExtra("url",items.getUrl());
            this.context.startActivity(intent);

        }
    }
}
