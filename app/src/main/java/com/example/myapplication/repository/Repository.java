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

    public Observable<NewsResponce> getNews(){
        return newsApiService.getNews("us",Credentials.API_Key);
    }

}
