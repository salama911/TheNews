package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.adapter.AdapterWorld;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private NewsViewModel newsViewModel,europeViewModel;
    private RecyclerView recyclerView , recyclerViewWorld;
    private Adapter adapter;
    private AdapterWorld adapterWorld;
    private ImageView rlaterImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView =findViewById(R.id.recycler_egynews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter=new Adapter(this);
        recyclerView.setAdapter(adapter);

        recyclerViewWorld =findViewById(R.id.recycler_Worldnews);
        adapterWorld=new AdapterWorld(this);
        recyclerViewWorld.setAdapter(adapterWorld);

      /*  rlaterImg =findViewById(R.id.star_read_latter);
        rlaterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
            }
        });*/


        getEgyNews();
        getEuroNews();

    }

    private void getEgyNews(){
        newsViewModel =new ViewModelProvider(this).get(NewsViewModel.class);

        newsViewModel.getNews();

        newsViewModel.newsList.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                adapter.setList(newsModels);

            }
        });
    }

    private void getEuroNews(){
        europeViewModel =new ViewModelProvider(this).get(NewsViewModel.class);

        europeViewModel.getEuropeNews();

        europeViewModel.EuropenewsList.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                adapterWorld.setList(newsModels);

            }
        });
    }


}