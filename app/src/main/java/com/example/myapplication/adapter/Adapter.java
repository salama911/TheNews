package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<NewsModel> newsList = new ArrayList<>();
    private Context mcontext;


    public Adapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Newstitle.setText(newsList.get(position).getTitle());

        Glide.with(mcontext)
                .load(newsList.get(position).getUrlToImage())
                .into(holder.NewsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setList(List<NewsModel>modelList){
        this.newsList=modelList;
        notifyDataSetChanged();
    }

    /*public Pockemon getPockemonAt(int position){
        return pockemonList.get(position);

    }*/


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Newstitle,NewsDescription;
        ImageView NewsImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Newstitle = itemView.findViewById(R.id.news_title);
            NewsImage = itemView.findViewById(R.id.theNew_image);




        }
    }
}