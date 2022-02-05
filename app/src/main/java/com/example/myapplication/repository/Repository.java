package com.example.myapplication.repository;

import com.example.myapplication.R;
import com.example.myapplication.network.NewsApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    public NewsApiService newsApiService;

    @Inject
    public Repository(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    public Observable<NewsApiService> getNews(){
        return newsApiService.getNews("eg","f4de97aa77ca4dd692729ea3087f191e");
    }

}
