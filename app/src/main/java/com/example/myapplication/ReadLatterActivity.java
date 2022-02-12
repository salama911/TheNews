package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReadLatterActivity extends AppCompatActivity {

    private NewsViewModel favVmodel;
    private RecyclerView recyclerView ;
    private Adapter adapter;
    private Button tohome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_latter);

        recyclerView=findViewById(R.id.recyclerView_readlater);
        adapter= new Adapter(this);
        recyclerView.setAdapter(adapter);

        tohome = findViewById(R.id.gotoHome);
        tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadLatterActivity.this, MainActivity.class));
            }
        });

        setupSwip();
        favVmodel =new ViewModelProvider(this).get(NewsViewModel.class);

        favVmodel.getReadlaterNews();

        favVmodel.getReadLaterList().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                adapter.setList(newsModels);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void setupSwip(){

        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPokemonPosition =viewHolder.getAdapterPosition();
                NewsModel swipedPokemon=adapter.getNewsAt(swipedPokemonPosition);
                favVmodel.DaleteAnew(swipedPokemon.getTitle());
                adapter.notifyDataSetChanged();
                Toast.makeText(ReadLatterActivity.this, "Deleted From Read Later List ", Toast.LENGTH_SHORT).show();


            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

}

