package com.example.myapplication.di;

import android.app.Application;

import androidx.room.Room;

import com.example.myapplication.db.Dao;
import com.example.myapplication.db.NewsDB;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    //dagger provides me with Database(NewsDatabase)
    @Provides
    @Singleton
    public static NewsDB provideDB(Application application){
        return Room.databaseBuilder(application,NewsDB.class,"RlatterDb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

    //dagger provides me with Dao
    @Provides
    @Singleton
    public static Dao provideDao(NewsDB newsDB){
        return newsDB.dao();
    }

}
