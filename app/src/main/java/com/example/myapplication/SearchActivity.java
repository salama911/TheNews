package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.adapter.OnNewListener;
import com.example.myapplication.adapter.SearchAdapter;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements OnNewListener {

    private SearchView searchView;
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;
    private NewsViewModel newsViewmodelsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

      //  newsViewmodelsearch =new ViewModelProvider(SearchActivity.this).get(NewsViewModel.class);
        recyclerView = findViewById(R.id.search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(getApplicationContext(), SearchActivity.this);
        recyclerView.setAdapter(searchAdapter);
        searchView = findViewById(R.id.search_view);




       /* newsViewmodelsearch.searchAnewlist.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                searchAdapter.setList(newsModels);
            }
        });*/

    }







@Override
public void OnNewClick(int position){

        }

@Override
public void OnEuroNewClick(int position){

        }

@Override
public void OnRlaterNewClick(int position){

        }
        }