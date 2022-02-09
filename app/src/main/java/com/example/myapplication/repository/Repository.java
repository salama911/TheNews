package com.example.myapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.myapplication.db.Dao;
import com.example.myapplication.model.Credentials;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.model.NewsResponce;
import com.example.myapplication.network.NewsApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    public NewsApiService newsApiService;
    public Dao dao;

    @Inject
    public Repository(NewsApiService newsApiService,Dao dao) {
        this.newsApiService = newsApiService;
        this.dao=dao;
    }


    //News Of Egypt
    public Observable<NewsResponce> getNews(){
        return newsApiService.getNews("eg",Credentials.API_Key);
    }

    //News of europe
    public Observable<NewsResponce> getEuropeNews(){
        return newsApiService.getNews("gb",Credentials.API_Key);
    }


    public void InsertAnew(NewsModel newsModel){dao.InsertNews(newsModel);}

    public void DeleteAnew(String newsTitle){dao.deleteAnew(newsTitle);}

    public LiveData<List<NewsModel>>getRlatter(){
        return dao.getRlatterNews();
    }


}
