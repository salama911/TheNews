package com.example.myapplication.repository;

import com.example.myapplication.model.Credentials;
import com.example.myapplication.model.NewsResponce;
import com.example.myapplication.network.NewsApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    public NewsApiService newsApiService;

    @Inject
    public Repository(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }


    //News Of Egypt
    public Observable<NewsResponce> getNews(){
        return newsApiService.getNews("eg",Credentials.API_Key);
    }

    //News of europe
    public Observable<NewsResponce> getEuropeNews(){
        return newsApiService.getNews("gb",Credentials.API_Key);
    }


}
