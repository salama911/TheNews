package com.example.myapplication.adapter;

import static com.example.myapplication.R.drawable.ic_star_readlatteryellow;

import android.content.Context;
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

public class AdapterWorld extends RecyclerView.Adapter<AdapterWorld.MyViewHolder> {

    private List<NewsModel> newsList = new ArrayList<>();
    private Context mcontext;
    private NewsViewModel newsViewModel;
    private OnNewListener onNewListener;

    public AdapterWorld(Context mcontext , OnNewListener onNewListener) {
        this.mcontext = mcontext;
        this.onNewListener=onNewListener;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_europe_item, parent, false),onNewListener);

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Newstitle.setText(newsList.get(position).getTitle());

        Glide.with(mcontext)
                .load(newsList.get(position).getUrlToImage())
                .into(holder.NewsImage);

        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.star.setImageResource(R.drawable.ic_euro_star);
                NewsModel newsModelss=newsList.get(position);
                newsViewModel.InsertAnew(newsModelss);
                Toast.makeText(mcontext, " Added To Read Later List", Toast.LENGTH_SHORT).show();
            }
        });


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

    public void setList(List<NewsModel>modelList){
        this.newsList=modelList;
        notifyDataSetChanged();
    }

    public NewsModel getSelectedMovie(int position){

        if(newsList!=null){
            if( newsList.size()>0){
                return newsList.get(position);
            }
        }
        return null;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Newstitle;
        ImageView NewsImage;
        ImageView star;
        OnNewListener onNewListener;



        public MyViewHolder(@NonNull View itemView,OnNewListener onNewListener) {
            super(itemView);


            this.onNewListener=onNewListener;
            Newstitle = itemView.findViewById(R.id.news_title);
            NewsImage = itemView.findViewById(R.id.news_euro_image);
            star = itemView.findViewById(R.id.yellow_star_euro);


        itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onNewListener.OnEuroNewClick(getAdapterPosition());
        }
    }
}