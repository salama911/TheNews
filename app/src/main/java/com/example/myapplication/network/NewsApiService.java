package com.example.myapplication.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Observable <NewsApiService> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
