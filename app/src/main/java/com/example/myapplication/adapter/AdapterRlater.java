package com.example.myapplication.adapter;

import static com.example.myapplication.R.drawable.ic_star_readlatteryellow;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterRlater extends RecyclerView.Adapter<AdapterRlater.MyViewHolder> {
    private List<NewsModel> newsList = new ArrayList<>();
    private Context mcontext;
    private NewsViewModel newsViewModel;


    public AdapterRlater(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rlater_news_item, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Newstitle.setText(newsList.get(position).getTitle());

        Glide.with(mcontext)
                .load(newsList.get(position).getUrlToImage())
                .into(holder.NewsImage);



    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if(newsViewModel==null){
            newsViewModel=new ViewModelProvider((ViewModelStoreOwner) recyclerView.getContext()).get(NewsViewModel.class);
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setList(List<NewsModel> modelList) {
        this.newsList = modelList;
        notifyDataSetChanged();
    }

    public NewsModel getNewsAt(int position) {
        return newsList.get(position);

    }






    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Newstitle, NewsDescription;
        ImageView NewsImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Newstitle = itemView.findViewById(R.id.news_title);
            NewsImage = itemView.findViewById(R.id.theNew_image);

        }
    }

}
