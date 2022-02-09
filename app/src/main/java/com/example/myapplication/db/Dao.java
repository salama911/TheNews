package com.example.myapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.NewsModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    public Void InsertNews(NewsModel newsModel);


    @Query("delete from ReadLatter_table where title =:newsTitle")
    public void deleteAnew(String newsTitle);


    @Query("select * from readlatter_table")
    public LiveData<List<NewsModel>>getRlatterNews();

}
