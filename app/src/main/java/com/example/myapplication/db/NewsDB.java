package com.example.myapplication.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.NewsModel;

@Database(entities = NewsModel.class,version =1,exportSchema = false )
public abstract class NewsDB extends RoomDatabase {

    public abstract Dao dao();

}
