package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class SearchActivity2 extends AppCompatActivity {
    androidx.appcompat.widget.SearchView ser;
    RecyclerView recyclerView2;
  NewsViewModel newsviewmodelsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

       ser=findViewById(R.id.search_view2);

      /*  newsviewmodelsearch = new ViewModelProvider(this).get(NewsViewModel.class);
        newsviewmodelsearch.init(this.getApplication());*/

        newsviewmodelsearch=new ViewModelProvider(this).get(NewsViewModel.class);
        newsviewmodelsearch.searchAnewlist.observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                Log.v("tagsearch",""+newsModels);
            }
        });


        final androidx.appcompat.widget.SearchView searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsviewmodelsearch.searrchNews(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



    }
}