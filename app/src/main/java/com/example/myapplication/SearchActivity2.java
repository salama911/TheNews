package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.adapter.OnNewListener;
import com.example.myapplication.adapter.SearchAdapter;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.model.NewsResponce;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity2 extends AppCompatActivity implements OnNewListener{

    private SearchView searchView;
    RecyclerView recyclerView2;
    NewsViewModel newsviewmodelsearch;
    SearchAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        searchView = findViewById(R.id.search_view2);
        recyclerView2 = findViewById(R.id.search_recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter= new SearchAdapter(this, this);
        recyclerView2.setAdapter(adapter);

        newsviewmodelsearch = new  ViewModelProvider(this).get(NewsViewModel.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsviewmodelsearch.getsearchedNews(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        newsviewmodelsearch.searchAnewlist.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
               adapter.setList(newsModels);
                Log.v("tagsw","searched News"+newsModels);

            }
        });






    }

    @Override
    public void OnNewClick(int position) {

    }

    @Override
    public void OnEuroNewClick(int position) {

    }

    @Override
    public void OnRlaterNewClick(int position) {

    }

    @Override
    public void OnSearchedNewClick(int position) {
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("searchedNews",adapter.getSelectedMovie(position));
        startActivity(intent);
    }
}