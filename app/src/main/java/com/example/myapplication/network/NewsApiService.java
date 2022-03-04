package com.example.myapplication.network;

import com.example.myapplication.model.NewsResponce;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Observable<NewsResponce> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Observable<NewsResponce> searchAnew(
            @Query("q") String query,
            @Query("from") String from,
            @Query("sortBy") String sort,
            @Query("apiKey") String search_apiKey
    );


}


//api of searching for news::
//https://newsapi.org/v2/everything?q=tesla&from=2022-02-02
// &sortBy=publishedAt&apiKey=f4de97aa77ca4dd692729ea3087f191e