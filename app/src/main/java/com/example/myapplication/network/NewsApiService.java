package com.example.myapplication.network;

import com.example.myapplication.model.NewsResponce;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Observable <NewsResponce> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
