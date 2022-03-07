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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private List<NewsModel> searchednewsList = new ArrayList<>();
    private Context mcontext;
    private OnNewListener onNewListener;

    public SearchAdapter(Context mcontext, OnNewListener onNewListener) {
        this.mcontext = mcontext;
        this.onNewListener=onNewListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_news_item, parent, false),onNewListener);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.serchedNewstitle.setText(searchednewsList.get(position).getTitle());

        Glide.with(mcontext)
                .load(searchednewsList.get(position).getUrlToImage())
                .into(holder.searchedNewsImage);


    }
   /* @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if(newsViewModel==null){
            newsViewModel=new ViewModelProvider((ViewModelStoreOwner) recyclerView.getContext()).get(NewsViewModel.class);
        }
    }*/


    @Override
    public int getItemCount() {
        return searchednewsList.size();
    }

    public void setList(List<NewsModel> modelList) {
        this.searchednewsList = modelList;
        notifyDataSetChanged();
    }

    public NewsModel getNewsAt(int position) {
        return searchednewsList.get(position);

    }

    public NewsModel getSelectedMovie(int position){

        if(searchednewsList!=null){
            if( searchednewsList.size()>0){
                return searchednewsList.get(position);
            }
        }
        return null;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView serchedNewstitle;
        ImageView searchedNewsImage;
        ImageView searchednewsstar;
        OnNewListener onNewListener;



        public MyViewHolder(@NonNull View itemView,OnNewListener onNewListener) {
            super(itemView);

            this.onNewListener=onNewListener;
            serchedNewstitle = itemView.findViewById(R.id.searched_news_title);
            searchedNewsImage = itemView.findViewById(R.id.searched_news_image);
            searchednewsstar = itemView.findViewById(R.id.searched_news_star);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onNewListener.OnSearchedNewClick(getAdapterPosition());
        }
    }

}
