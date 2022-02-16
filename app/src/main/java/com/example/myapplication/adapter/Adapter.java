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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<NewsModel> newsList = new ArrayList<>();
    private Context mcontext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private boolean isliked=false;

    private NewsViewModel newsViewModel;
    private OnNewListener onNewListener;

    public Adapter(Context mcontext,OnNewListener onNewListener) {
        this.mcontext = mcontext;
        this.onNewListener=onNewListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false),onNewListener);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Newstitle.setText(newsList.get(position).getTitle());

        Glide.with(mcontext)
                .load(newsList.get(position).getUrlToImage())
                .into(holder.NewsImage);


        holder.rlaterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.rlaterImg.setBackgroundResource(ic_star_readlatteryellow);
                NewsModel newsModelss=newsList.get(position);
                newsViewModel.InsertAnew(newsModelss);
                Toast.makeText(mcontext, " Added To Read Later List", Toast.LENGTH_SHORT).show();
               /* if (isliked){
                    holder.rlaterImg.setBackgroundResource(ic_star_readlatteryellow);
                    sharedPreferences = mcontext.getSharedPreferences("imageColor", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putInt("color", ic_star_readlatteryellow);
                    editor.apply();
                    isliked=true;
                }
                else{
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mcontext);
                    int x=sharedPref.getInt("color",0);
                    holder.rlaterImg.setBackgroundResource(x);*/

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

    public void setList(List<NewsModel> modelList) {
        this.newsList = modelList;
        notifyDataSetChanged();
    }

    public NewsModel getNewsAt(int position) {
        return newsList.get(position);

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
        ImageView rlaterImg;
        OnNewListener onNewListener;



        public MyViewHolder(@NonNull View itemView,OnNewListener onNewListener) {
            super(itemView);

            this.onNewListener=onNewListener;
            Newstitle = itemView.findViewById(R.id.news_title);
            NewsImage = itemView.findViewById(R.id.theNew_image);
            rlaterImg = itemView.findViewById(R.id.star_read_latter);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onNewListener.OnNewClick(getAdapterPosition());
        }
    }

}
