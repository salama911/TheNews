package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.adapter.AdapterWorld;
import com.example.myapplication.adapter.OnNewListener;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements OnNewListener  {
    private NewsViewModel favVmodel;

    private NewsViewModel newsViewModel,europeViewModel;
    private RecyclerView recyclerView , recyclerViewWorld;
    private Adapter adapter;
    private AdapterWorld adapterWorld;
    private Button rlaterButton ,searchAnew;
    ArrayList<NewsModel>roomlist=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchAnew=findViewById(R.id.searchAnew);
        searchAnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchActivity2.class);
                startActivity(intent);
            }
        });

        favVmodel =new ViewModelProvider(this).get(NewsViewModel.class);
        favVmodel.getReadlaterNews();

        recyclerView =findViewById(R.id.recycler_egynews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter=new Adapter(getApplicationContext(),MainActivity.this,roomlist);
        recyclerView.setAdapter(adapter);

       // get roomed data and passingit to adapter to use it later for make star colored
        favVmodel.getReadLaterList().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                roomlist= (ArrayList<NewsModel>) newsModels;
                Log.d("tagroom","list"+roomlist);
                 adapter=new Adapter(getApplicationContext(),MainActivity.this,roomlist);
            }
        });

        recyclerViewWorld =findViewById(R.id.recycler_Worldnews);
        adapterWorld=new AdapterWorld(this,this);
        recyclerViewWorld.setAdapter(adapterWorld);


        rlaterButton=findViewById(R.id.toreadlaterbtn);
        rlaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReadLatterActivity.class));
            }
        });

        getEgyNews();
        getEuroNews();
       // setupSwip();


        //get roomed data and passing it to adapter to use it later for make star colored


    }



    private void getEgyNews(){
        newsViewModel =new ViewModelProvider(this).get(NewsViewModel.class);

       newsViewModel.getNews();

        newsViewModel.newsList.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                adapter.setList(newsModels);
                recyclerView.setAdapter(adapter);
                Log.v("tags","searched News"+newsModels);


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


    @Override
    public void OnNewClick(int position) {
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("News",adapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void OnEuroNewClick(int position) {
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("EuroNews",adapterWorld.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void OnRlaterNewClick(int position) {

    }

    @Override
    public void OnSearchedNewClick(int position) {

    }


}